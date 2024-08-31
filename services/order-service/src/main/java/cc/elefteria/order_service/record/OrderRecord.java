package cc.elefteria.order_service.record;

import cc.elefteria.order_service.entity.Order;
import cc.elefteria.order_service.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record OrderRecord(
    Integer id,
    String reference,
    @NotNull(message = "Order amount is required")
    @Positive(message = "Order amount should be positive")
    BigDecimal totalAmount,
    @NotNull(message = "Payment method is required")
    PaymentMethod paymentMethod,
    @NotBlank(message = "Customer is required")
    String customerId,
    @NotEmpty(message = "Order needs to contain at least one product")
    List<PurchaseRequest> products
) {
    public Order toOrder() {
        return Order.builder()
            .id(this.id)
            .customerId(this.customerId)
            .reference(this.reference)
            .totalAmount(this.totalAmount)
            .paymentMethod(this.paymentMethod)
            .build();
    }
    
    public static OrderRecord fromOrder(Order order) {
        return new OrderRecord(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId(),
            order.getOrderLines().stream().map(ol -> new PurchaseRequest(ol.getProductId(), ol.getQuantity())).toList()
        );
    }
}
