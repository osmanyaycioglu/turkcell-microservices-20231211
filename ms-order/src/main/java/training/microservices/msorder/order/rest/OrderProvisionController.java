package training.microservices.msorder.order.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import training.microservices.msorder.order.rest.models.OrderDto;

@RestController
@RequestMapping("/api/v1/order/provision")
public class OrderProvisionController {

    @PostMapping("/place")
    @Operation(summary = "Order ın girildiği yer",description = "Order gir askjhdkajshddhasd")
    public String place(@Valid @RequestBody OrderDto orderDtoParam){
        return "OK";
    }

    @GetMapping("/cancel")
    public String place(@RequestParam String orderId){
        return "OK";
    }

    @GetMapping("/pause")
    public String pause(@RequestParam String orderId){
        return "OK";
    }

}
