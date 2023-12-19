package training.microservices.msnotify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/test")
@RefreshScope
public class RefreshTestController {

    @Value("${refresh.test}")
    private String testStr;

    @GetMapping("/test1")
    public String test1(){
        return testStr;
    }
}
