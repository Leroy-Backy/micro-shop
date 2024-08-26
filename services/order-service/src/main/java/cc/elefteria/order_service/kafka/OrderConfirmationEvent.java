package cc.elefteria.order_service.kafka;

import cc.elefteria.order_service.enums.PaymentMethod;
import cc.elefteria.order_service.record.CustomerRecord;
import cc.elefteria.order_service.record.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationEvent(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    CustomerRecord customer,
    List<PurchaseResponse> products
) {
}
