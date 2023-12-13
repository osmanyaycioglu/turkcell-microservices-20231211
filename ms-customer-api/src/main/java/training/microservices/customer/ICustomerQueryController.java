package training.microservices.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICustomerQueryController {

    @GetMapping("/api/v1/customer/query/find/by/phone")
    public Customer findOneCustomerByPhoneNumber(@RequestParam String number);

}
