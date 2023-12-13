package training.microservices.msorder.order.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.microservices.msorder.order.rest.models.OrderDto;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/order/provision")
public class OrderProvisionController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/place")
    @Operation(summary = "Order ın girildiği yer",description = "Order gir askjhdkajshddhasd")
    @ResponseStatus(HttpStatus.CREATED)
    public String place(@Valid @RequestBody OrderDto orderDtoParam){
        return "OK-" + port;
    }

    @GetMapping("/cancel")
    public String place(@RequestParam String orderId){
        return "OK";
    }

    @GetMapping("/pause")
    public String pause(@RequestParam String orderId){
        return "OK";
    }

    // Don't do this
    // @GetMapping("/{op}")
    public ResponseEntity<?> pause(@PathVariable String op,
                                   HttpServletRequest requestParam){
        switch (op) {
            case "cancel":
                String orderIdLoc = requestParam.getParameter("orderId");
                return ResponseEntity.status(HttpStatus.OK).body("OK");
            case "place":
                try {
                    ServletInputStream inputStreamLoc = requestParam.getInputStream();
                    ObjectMapper objectMapperLoc = new ObjectMapper();
                    OrderDto           orderDtoLoc    = objectMapperLoc.readValue(inputStreamLoc,
                                                                                  OrderDto.class);
                    return ResponseEntity.status(HttpStatus.OK).body("OK");
                } catch (IOException eParam) {
                    throw new RuntimeException(eParam);
                }
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
