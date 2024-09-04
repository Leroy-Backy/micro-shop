package cc.elefteria.payment_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
  private final KafkaTemplate<String, PaymentNotificationEvent> kafkaTemplate;
  
  public void sendPaymentNotification(PaymentNotificationEvent notificationEvent) {
    log.info("Sending payment notification: <{}>", notificationEvent);
    kafkaTemplate.send("payment-notification", notificationEvent);
  }
}
