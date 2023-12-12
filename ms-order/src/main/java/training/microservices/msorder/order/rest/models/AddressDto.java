package training.microservices.msorder.order.rest.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import training.microservices.msorder.validation.BadWords;

@Data
public class AddressDto {
    @NotEmpty
    @NotBlank
    @BadWords({"adn", "bbb", "aaa"})
    @Schema(name = "city",description = "Oturulan şehir",example = "istanbul")
    private String city;
    @NotEmpty
    @NotBlank
    @Schema(name = "street",description = "Oturulan cadde",example = "ataşehir")
    private String street;
}
