package cc.elefteria.notification_service.kafka.order;

import java.math.BigDecimal;

public record PurchaseResponse(
    Integer id,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {
}
