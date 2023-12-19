package training.microservices.msnotify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RefreshTestController {

    @Value("${refresh.test}")
    private String testStr;

    @GetMapping("/test1")
    public String test1(){
        return testStr;
    }
}
