package training.microservices.msorder.integration.customer;

import org.springframework.cloud.openfeign.FeignClient;
import training.microservices.customer.ICustomerQueryController;

@FeignClient(value = "CUSTOMER", contextId = "context1")
public interface ICustomerQueryRestClient extends ICustomerQueryController {
}
