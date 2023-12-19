package training.microservices.mscommon.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorClientConfig {

    @Bean
    public GeneralErrorDecoder generalErrorDecoder(){
        return new GeneralErrorDecoder();
    }

}
