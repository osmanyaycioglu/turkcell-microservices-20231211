package training.microservices.msorder.order.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import training.microservices.msorder.order.rest.models.AddressDto;
import training.microservices.msorder.order.rest.models.MealDto;
import training.microservices.msorder.validation.BadWords;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String        phoneNumber;
    private String        customerName;
    private String        customerSurname;
    private List<Meal>    meals;
    private Address       address;
    private LocalDateTime orderDate;
    private LocalDateTime orderFinishDate;
    private EOrderStatus  orderStatus;

}
