package training.microservices.msorder.integration.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import training.microservices.customer.Customer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CustomerQueryIntegration {
    private final RestTemplate restTemplate;
    private final ICustomerQueryRestClient customerQueryRestClient;
    private final EurekaDiscoveryClient eurekaDiscoveryClient;


    public Customer findCustomer(String phoneNumber) {
        Customer customerLoc = restTemplate.getForObject(
                "http://CUSTOMER/api/v1/customer/query/find/by/phone?number="
                + phoneNumber,
                Customer.class);

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

        return null;
    }

}
