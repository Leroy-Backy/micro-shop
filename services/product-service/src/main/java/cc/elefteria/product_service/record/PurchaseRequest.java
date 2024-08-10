package cc.elefteria.product_service.record;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
    @NotNull(message = "Product id is required")
    Integer productId,
    @NotNull(message = "Product quantity is required")
    Integer quantity
) {
}
