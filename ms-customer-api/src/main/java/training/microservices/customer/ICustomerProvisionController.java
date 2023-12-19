package training.microservices.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICustomerProvisionController {

    @PostMapping("/api/v1/customer/provision/add")
    public String add(@RequestBody Customer customerParam);

}
