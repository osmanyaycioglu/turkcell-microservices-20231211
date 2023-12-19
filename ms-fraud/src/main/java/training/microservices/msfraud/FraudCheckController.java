package training.microservices.msfraud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud/check")
public class FraudCheckController {

    @Value("${server.port}")
    private int port;

    @PostMapping("/customer")
    public FraudResponse checkCustomer(@RequestBody Customer customerParam) {
        return new FraudResponse("Normal customer-" + port,
                                 0);
    }

}
