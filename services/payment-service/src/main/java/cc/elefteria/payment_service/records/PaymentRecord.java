package cc.elefteria.payment_service.records;

import cc.elefteria.payment_service.entity.Payment;
import cc.elefteria.payment_service.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRecord(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerRecord customer
) {
  
  public Payment toPayment() {
    return Payment.builder()
        .id(id)
        .amount(amount)
        .paymentMethod(paymentMethod)
        .orderId(orderId)
        .build();
  }
}
