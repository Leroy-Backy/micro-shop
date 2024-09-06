package cc.elefteria.notification_service.kafka.payment;

import cc.elefteria.notification_service.kafka.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationEvent(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
