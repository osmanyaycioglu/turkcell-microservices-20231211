package training.microservices.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class Customer {
    private Long   customerId;
    private String customerNumber;
    private String firstName;
    @Setter
    private String lastName;
    private Double discount;
}
