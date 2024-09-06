package cc.elefteria.notification_service.kafka.order;

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
