package training.ms.test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Starter implements ApplicationRunner {
    private final MyTest                 myTest;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class,
                              args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        CircuitBreaker                circuitBreakerLoc   = circuitBreakerRegistry.circuitBreaker("cc-customer-query");
        CircuitBreaker.EventPublisher ccEventPublisherLoc = circuitBreakerLoc.getEventPublisher();
        ccEventPublisherLoc.onStateTransition(e -> System.out.println("State changed : " + e.toString()));

        Retry       retryLoc       = retryRegistry.retry("customer-query");
        RetryConfig retryConfigLoc = retryLoc.getRetryConfig();
        System.out.println("Retry : " + retryConfigLoc);
        Retry.EventPublisher eventPublisherLoc = retryLoc.getEventPublisher();
        eventPublisherLoc.onRetry(e -> System.out.println("************Retried!!"))
                         .onSuccess(e -> System.out.println("------------Success"));
        Retry.Metrics metricsLoc = retryLoc.getMetrics();
        for (int i = 0; i < 50; i++) {
            System.out.println("----------------*!*-------------");
            try {
                Thread.sleep(500);
            } catch (InterruptedException eParam) {
            }
            System.out.println("Calling findCustomer : " + i);
            try {
                myTest.findCustomer("5435023131");
            } catch (Exception eParam) {
                System.out.println("___________FAILED!!!");
            }

            System.out.println("[Retry] S:"
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " SR: "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " F:"
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " FR:"
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
            );
            CircuitBreaker.Metrics ccMetricsLoc = circuitBreakerLoc.getMetrics();
            System.out.println("{CC} State : "
                               + circuitBreakerLoc.getState()
                               + " FR : "
                               + ccMetricsLoc.getFailureRate()
                               + " F:"
                               + ccMetricsLoc.getNumberOfFailedCalls()
                               + " S:"
                               + ccMetricsLoc.getNumberOfSuccessfulCalls()
                               + " NP:"
                               + ccMetricsLoc.getNumberOfNotPermittedCalls()
            );
            System.out.println("--------------------------------");
        }
    }
}
