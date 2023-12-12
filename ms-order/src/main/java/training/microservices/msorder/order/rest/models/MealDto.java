package training.microservices.msorder.order.rest.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MealDto {
    @NotBlank
    private String mealName;
    @DecimalMin("1")
    @DecimalMax("10")
    private Float  portion;

}
