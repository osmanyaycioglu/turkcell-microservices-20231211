package training.microservices.mscustomer;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import training.microservices.customer.Customer;
import training.microservices.customer.ICustomerProvisionController;
import training.microservices.mscommon.error.ErrorObj;
import training.microservices.mscommon.error.RemoteCallException;

@RestController
@RequiredArgsConstructor
public class CustomerProvisionController implements ICustomerProvisionController {
    private final WebClient.Builder                         webClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction filterFunction;

    @PostMapping(value = "/api/v1/customer/provision/flux/add")
    public Mono<String> addCustomer(final Customer customerParam) {
        HttpClient httpClientLoc = HttpClient.create()
                                             .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                                                     2000);
        WebClient buildLoc = webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClientLoc))
                                             .defaultHeader("abc",
                                                            "osman")
                                             .build();

        WebClient webClientLoc = WebClient.builder()
                                          .clientConnector(new ReactorClientHttpConnector(httpClientLoc))
                                          .defaultHeader("abc",
                                                         "osman")
                                          .filter(filterFunction)
                                          .build();
        return webClientLoc.post()
                           .uri("http://FRAUD/api/v1/fraud/check/customer")
                           .body(BodyInserters.fromValue(customerParam))
                           .retrieve()
                           .onStatus(HttpStatusCode::isError,
                                     r -> r.bodyToMono(ErrorObj.class)
                                           .flatMap(e -> Mono.error(new RemoteCallException(e))))
                           .bodyToMono(FraudResponse.class)
                           .doOnNext(f -> System.out.println("Received : " + f))
                           .flatMap(f -> Mono.just(f.getDesc()));
    }

    @Override
    public String add(final Customer customerParam) {
        return addCustomer(customerParam).block();
    }

}
