package training.microservices.msorder.facades;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training.microservices.customer.Customer;
import training.microservices.msorder.integration.customer.CustomerIntegration;

@Service
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerIntegration customerIntegration;

    @CircuitBreaker(name = "cc-customer-query", fallbackMethod = "checkAndCreateCustomerFallback")
    public Customer checkAndCreateCustomer(String phoneNumber,
                                           String name,
                                           String surname) {
        Customer customerLoc = customerIntegration.findCustomerWithFeignClient(phoneNumber);
        if (customerLoc == null) {
            customerLoc = Customer.builder()
                                  .withCustomerNumber(phoneNumber)
                                  .withFirstName(name)
                                  .withLastName(surname)
                                  .build();
            customerIntegration.addCustomer(customerLoc);
        }
        return customerLoc;
    }

    public Customer checkAndCreateCustomerFallback(String phoneNumber,
                                                   String name,
                                                   String surname,
                                                   Exception exp) {
        return Customer.builder()
                       .withCustomerNumber(phoneNumber)
                       .withFirstName(name)
                       .withLastName(surname)
                       .build();

    }
}
