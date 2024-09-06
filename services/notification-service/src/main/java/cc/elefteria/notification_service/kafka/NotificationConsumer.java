package cc.elefteria.notification_service.kafka;

import cc.elefteria.notification_service.email.EmailService;
import cc.elefteria.notification_service.entity.Notification;
import cc.elefteria.notification_service.enums.NotificationType;
import cc.elefteria.notification_service.kafka.order.OrderConfirmationEvent;
import cc.elefteria.notification_service.kafka.payment.PaymentNotificationEvent;
import cc.elefteria.notification_service.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
  private final NotificationRepository notificationRepository;
  
  private final EmailService emailService;
  
  @KafkaListener(topics = "payment-topic")
  public void consumePaymentSuccessNotification(PaymentNotificationEvent paymentNotificationEvent) throws MessagingException {
    log.info("Consuming the message from payment topic <{}>", paymentNotificationEvent);
    notificationRepository.save(
        Notification.builder()
            .type(NotificationType.PAYMENT_CONFIRMATION)
            .notificationDate(new Timestamp(System.currentTimeMillis()))
            .paymentConfirmation(paymentNotificationEvent)
            .build()
    );


    emailService.sendPaymentSuccessEmail(
        paymentNotificationEvent.customerEmail(),
        paymentNotificationEvent.customerFirstName() + " " + paymentNotificationEvent.customerLastName(),
        paymentNotificationEvent.amount(),
        paymentNotificationEvent.orderReference()
    );

  }

  @KafkaListener(topics = "order-topic")
  public void consumeOrderConfirmation(OrderConfirmationEvent orderConfirmationEvent) throws MessagingException {
    log.info("Consuming the message from order topic <{}>", orderConfirmationEvent);
    notificationRepository.save(
        Notification.builder()
            .type(NotificationType.ORDER_CONFIRMATION)
            .notificationDate(new Timestamp(System.currentTimeMillis()))
            .orderConfirmation(orderConfirmationEvent)
            .build()
    );

    emailService.sendOrderSuccessEmail(
        orderConfirmationEvent.customer().email(),
        orderConfirmationEvent.customer().firstName() + " " + orderConfirmationEvent.customer().lastName(),
        orderConfirmationEvent.amount(),
        orderConfirmationEvent.orderReference(),
        orderConfirmationEvent.products()
    );
  }
}
