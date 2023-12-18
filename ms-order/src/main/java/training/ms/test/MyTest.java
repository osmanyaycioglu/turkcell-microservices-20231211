package training.ms.test;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import training.microservices.customer.Customer;

@Component
public class MyTest {

    private int counter = 0;

    @Retry(name = "customer-query")
    @CircuitBreaker(name = "cc-customer-query")
    public Customer findCustomer(String phoneNumber) {
        counter++;
        System.out.println("findCustomer method counter : " + counter);
        if (counter % 3 == 0) {
            throw new IllegalArgumentException("deneme");
        }
        if (counter > 25) {
            throw new IllegalStateException();
        }
        return Customer.builder()
                       .withFirstName("osman")
                       .withLastName("yay")
                       .withCustomerId(100L)
                       .withCustomerNumber(phoneNumber)
                       .build();
    }

    public Customer findCustomerFallback(String phoneNumber,
                                         Exception exp) {
        System.out.println("(((((((((FALLBACK)))))))))))) - " + exp.getClass()
                                                                   .getSimpleName());
        return null;
    }

}
