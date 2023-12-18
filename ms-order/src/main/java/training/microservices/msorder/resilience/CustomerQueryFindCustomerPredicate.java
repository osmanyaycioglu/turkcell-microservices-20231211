package training.microservices.msorder.resilience;

import training.microservices.customer.Customer;

import java.util.function.Predicate;

public class CustomerQueryFindCustomerPredicate implements Predicate<Customer> {

    @Override
    public boolean test(final Customer customerParam) {
        if (customerParam == null) {
            return true;
        }
        if (customerParam.getFirstName() == null){
            return true;
        }
        return false;
    }
}
