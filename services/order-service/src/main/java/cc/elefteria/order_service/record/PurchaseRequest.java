package cc.elefteria.order_service.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
    @NotNull(message = "Product id is required")
    Integer productId,
    @NotNull(message = "Product quantity is required")
    @Positive(message = "Product quantity is invalid")
    double quantity
) {
}
