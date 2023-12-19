package training.microservices.msorder.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training.microservices.customer.Customer;
import training.microservices.msorder.facades.CustomerFacade;
import training.microservices.msorder.integration.customer.CustomerIntegration;
import training.microservices.msorder.integration.notify.NotifyIntegration;
import training.microservices.msorder.order.services.models.Order;

@Service
@RequiredArgsConstructor
public class OrderProvisionService {
    private final CustomerIntegration customerQueryIntegration;
    private final CustomerFacade      customerFacade;
    private final NotifyIntegration   notifyIntegration;


    public String placeOrder(Order orderParam) {
        Customer customerLoc = customerQueryIntegration.findCustomer(orderParam.getPhoneNumber());
        return "Sipariş verildi : " + customerLoc.getFirstName() + " " + customerLoc.getLastName();
    }

    public String placeOrder2(final Order orderParam) {
        Customer customerLoc = customerFacade.checkAndCreateCustomer(orderParam.getPhoneNumber(),
                                                                     orderParam.getCustomerName(),
                                                                     orderParam.getCustomerSurname());
        notifyIntegration.sendSMS("Order alındı",
                                  orderParam.getPhoneNumber(),
                                  "message.sms.tr.local");
        return "Feign Sipariş verildi : " + customerLoc.getFirstName() + " " + customerLoc.getLastName();
    }
}
