package cc.elefteria.notification_service.entity;


import cc.elefteria.notification_service.enums.NotificationType;
import cc.elefteria.notification_service.kafka.order.OrderConfirmationEvent;
import cc.elefteria.notification_service.kafka.payment.PaymentNotificationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Notification {
  
  @Id
  private String id;
  private NotificationType type;
  private Timestamp notificationDate;
  private OrderConfirmationEvent orderConfirmation;
  private PaymentNotificationEvent paymentConfirmation;
}
