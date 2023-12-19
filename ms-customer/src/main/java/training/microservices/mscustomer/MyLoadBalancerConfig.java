package training.microservices.mscustomer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplierBuilder;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class MyLoadBalancerConfig {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
                                                                                    ServiceInstanceListSupplier.class),
                                          name);
    }


    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                                          .withDiscoveryClient()
                                          .withCaching()
                                          .withWeighted()
                                          .withHealthChecks()
                                          .build(context);
    }

}
