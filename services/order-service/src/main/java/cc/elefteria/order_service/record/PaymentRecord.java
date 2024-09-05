package cc.elefteria.order_service.record;

import cc.elefteria.order_service.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRecord(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerRecord customer
) {
}
