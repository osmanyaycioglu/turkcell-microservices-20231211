package training.microservices.msorder.integration.customer;

import org.springframework.cloud.openfeign.FeignClient;
import training.microservices.customer.ICustomerProvisionController;

@FeignClient(value = "CUSTOMER",contextId = "context2")
public interface ICustomerProvisionRestClient extends ICustomerProvisionController {
}
