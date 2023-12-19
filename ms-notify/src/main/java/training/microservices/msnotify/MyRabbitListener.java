package training.microservices.msnotify;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-direct-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.DIRECT),
                    key = "message.sms"
            )
    },concurrency = "5"
    )
    public void handleSMSMessage(String stringParam) {
        System.out.println("Received SMS : " + stringParam + " Thread : " + Thread.currentThread()
                                                                                  .getName());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "email-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-direct-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.DIRECT),
                    key = "message.email"
            )
    })
    public void handleEMAILMessage(String stringParam) {
        System.out.println("Received EMAIL : " + stringParam + " Thread : " + Thread.currentThread()
                                                                                    .getName());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms-tr-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-topic-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.TOPIC),
                    key = "message.sms.tr.#"
            )
    })
    public void handleTopicMessageTR(Message msg) {
        System.out.println("Received TR messages : " + msg + " Thread : " + Thread.currentThread()
                                                                                    .getName());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms-all-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-topic-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.TOPIC),
                    key = "message.sms.#"
            )
    })
    @SendTo("message-response-ex/response.message.sms")
    public String handleTopicMessageAll(Message msg) {
        System.out.println("Received ALL SMS messages : " + msg + " Thread : " + Thread.currentThread()
                                                                                          .getName());
        return "SMS Sent to : " + msg.getDest();
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "message-all-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-topic-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.TOPIC),
                    key = "message.#"
            )
    })
    public void handleTopicAll(Message msg) {
        System.out.println("Received ALL messages : " + msg + " Thread : " + Thread.currentThread()
                                                                                           .getName());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms-eu-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "message-topic-ex",
                            durable = "true",
                            autoDelete = "false",
                            type = ExchangeTypes.TOPIC),
                    key = "message.sms.eu.#"
            )
    })
    public void handleTopicMessageEU(Message msg) {
        System.out.println("Received EU messages : " + msg + " Thread : " + Thread.currentThread()
                                                                                           .getName());
    }

}
