package training.microservices.msorder.order.rest.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import training.microservices.msorder.validation.BadWords;

import java.util.List;

@Data
public class OrderDto {
    @NotEmpty
    @NotBlank
    @Size(min = 10, max = 11, message = "Telefon 10 karakter yada 11 karakter olmalı")
    private String        phoneNumber;
    @NotEmpty
    @NotBlank
    @BadWords({"abc","qwe","123"})
    private String        customerName;
    @NotEmpty
    @NotBlank
    private String        customerSurname;
    @NotNull
    @Size(min = 1)
    @Valid
    private List<MealDto> meals;
    @NotNull
    @Valid
    private AddressDto    address;


}
