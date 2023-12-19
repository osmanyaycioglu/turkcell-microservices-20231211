package training.microservices.msfraud;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class Customer {
    private Long   customerId;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private Double discount;
}
