package training.microservices.msorder.order.services.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import training.microservices.msorder.validation.BadWords;

@Data
public class Address {
    private String city;
    private String street;
}
