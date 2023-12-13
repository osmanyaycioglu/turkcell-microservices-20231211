package training.microservices.msorder.order.services.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Meal {
    private String mealName;
    private Float  portion;

}
