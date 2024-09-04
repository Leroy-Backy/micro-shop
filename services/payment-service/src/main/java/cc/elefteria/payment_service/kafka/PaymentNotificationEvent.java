package cc.elefteria.payment_service.kafka;

import cc.elefteria.payment_service.enums.PaymentMethod;

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
