package training.microservices.msorder.order.queue;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderNotifyResponseListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms-response-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-response-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.DIRECT),
                    key = "response.message.sms"
            )
    })
    public void handleSMSMessage(String stringParam) {
        System.out.println("Received Reponse : " + stringParam);
    }


}
