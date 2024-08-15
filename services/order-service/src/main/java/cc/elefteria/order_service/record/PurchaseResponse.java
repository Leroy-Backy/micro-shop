package cc.elefteria.order_service.record;

import java.math.BigDecimal;

public record PurchaseResponse(
    Integer id,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {
}
