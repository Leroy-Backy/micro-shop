package cc.elefteria.order_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
  
  private final KafkaTemplate<String, OrderConfirmationEvent> kafkaTemplate;
  
  public void sendOrderConfirmation(OrderConfirmationEvent orderConfirmation) {
    log.info("Sending order confirmation");
    Message<OrderConfirmationEvent> message = MessageBuilder
        .withPayload(orderConfirmation)
        .setHeader(KafkaHeaders.TOPIC, "order-topic")
        .build();
    kafkaTemplate.send(message);
//    kafkaTemplate.send("order-topic", orderConfirmation);
  }
}
