package training.microservices.msorder.integration.customer;

import org.springframework.cloud.openfeign.FeignClient;
import training.microservices.customer.ICustomerQueryController;

@FeignClient("CUSTOMER")
public interface ICustomerQueryRestClient extends ICustomerQueryController {
}
