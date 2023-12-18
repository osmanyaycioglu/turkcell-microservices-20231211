package training.microservices.msorder.integration.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import training.microservices.customer.Customer;
import training.microservices.mscommon.error.ClientCallException;
import training.microservices.mscommon.error.ErrorObj;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CustomerQueryIntegration {
    private final RestTemplate             restTemplate;
    private final ICustomerQueryRestClient customerQueryRestClient;
    private final EurekaDiscoveryClient    eurekaDiscoveryClient;

    @Retry(name = "customer-query")
    @CircuitBreaker(name = "cc-customer-query")
    public Customer findCustomer(String phoneNumber) {
        return restTemplate.getForObject(
                "http://CUSTOMER/api/v1/customer/query/find/by/phone?number="
                + phoneNumber,
                Customer.class);
    }

    public Customer findCustomerForEyesOnly(String phoneNumber) {
        Customer customerLoc = null;
        try {
            customerLoc = restTemplate.getForObject(
                    "http://CUSTOMER/api/v1/customer/query/find/by/phone?number="
                    + phoneNumber,
                    Customer.class);
        } catch (RestClientResponseException eParam) {
            ObjectMapper objectMapperLoc = new ObjectMapper();
            try {
                ErrorObj errorObjLoc = objectMapperLoc.readValue(eParam.getResponseBodyAsByteArray(),
                                                                 ErrorObj.class);
                Integer errorCodeLoc = errorObjLoc.getErrorCode();
                switch (errorCodeLoc) {
                    case 1024:
                    case 1025:
                        return null;
                    case 1048:
                    case 1049:
                        throw new ClientCallException(errorObjLoc.getErrorDesc(),
                                                      errorObjLoc);
                }
            } catch (IOException exParam) {
                throw new RuntimeException(exParam);
            }
            throw new RuntimeException(eParam);
        }

        return customerLoc;
    }

    public Customer findCustomer2(String phoneNumber) {
        return customerQueryRestClient.findOneCustomerByPhoneNumber(phoneNumber);
    }

    private AtomicInteger count = new AtomicInteger();

    public Customer findCustomer3(String phoneNumber) {
        List<ServiceInstance> customerServicesLoc = eurekaDiscoveryClient.getInstances("CUSTOMER");
        if (customerServicesLoc != null && !customerServicesLoc.isEmpty()) {
            RestTemplate    restTemplateLoc    = new RestTemplate();
            int             index              = count.incrementAndGet() % customerServicesLoc.size();
            ServiceInstance serviceInstanceLoc = customerServicesLoc.get(index);
            Customer customerLoc = restTemplateLoc.getForObject("http://"
                                                                + serviceInstanceLoc.getHost()
                                                                + ":"
                                                                + serviceInstanceLoc.getPort()
                                                                + "/api/v1/customer/query/find/one?phone="
                                                                + phoneNumber,
                                                                Customer.class);
            return customerLoc;
        }
        return null;
    }

}
