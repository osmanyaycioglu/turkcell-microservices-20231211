package training.microservices.nodeapigworder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NodeApigwOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodeApigwOrderApplication.class,
                              args);
    }

}
