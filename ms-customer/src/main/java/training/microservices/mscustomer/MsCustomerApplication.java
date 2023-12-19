package training.microservices.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
import training.microservices.mscommon.error.ErrorConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import(ErrorConfig.class)
@LoadBalancerClient(value = "loadbalancer1",configuration = MyLoadBalancerConfig.class)
public class MsCustomerApplication {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MsCustomerApplication.class,
                              args);
    }

}
