package training.microservices.msorder.integration.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyIntegration {
    private final RabbitTemplate rabbitTemplate;

    public void sendSMS(String msg,
                        String phoneNumber,
                        String key) {
        rabbitTemplate.convertAndSend("message-topic-ex",
                                      key,
                                      new Message(msg,
                                                  phoneNumber));

    }

}
