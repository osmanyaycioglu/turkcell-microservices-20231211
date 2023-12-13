package training.microservices.mscustomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import training.microservices.customer.Customer;
import training.microservices.customer.ICustomerQueryController;

import java.security.SecureRandom;
import java.util.Random;

@RestController
public class CustomerQueryController implements ICustomerQueryController {

    @Value("${server.port}")
    private int port;

    public Customer findOneCustomerByPhoneNumber(@RequestParam String number) {
        Random randomLoc = new SecureRandom();
        return Customer.builder()
                       .withCustomerId(randomLoc.nextLong())
                       .withCustomerNumber(number)
                       .withFirstName("Test" + randomLoc.nextInt())
                       .withLastName("Testor-" + port)
                       .build();
    }

}
