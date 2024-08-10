package cc.elefteria.product_service.record;

import cc.elefteria.product_service.entity.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRecord(
    Integer id,
    @NotNull(message = "Product name is required")
    String name,
    @NotNull(message = "Product description is required")
    String description,
    @NotNull(message = "Product quantity is required")
    @Positive(message = "Product quantity should be positive")
    double quantity,
    @NotNull(message = "Product price is required")
    @Positive(message = "Product price should be positive")
    BigDecimal price,
    @NotNull(message = "Product name is required")
    Integer categoryId,
    String categoryName,
    String categoryDescription
) {
    public ProductRecord(Integer id, String name, String description, double quantity, BigDecimal price) {
        this(id, name, description, quantity, price, null, null, null);
    }
    
    public static ProductRecord fromProduct(Product product) {
        return new ProductRecord(
            product.getId(), 
            product.getName(), 
            product.getDescription(), 
            product.getQuantity(), 
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription()
        );
    }

    public static ProductRecord fromProductPurchase(Product product, double quantity) {
        return new ProductRecord(
            product.getId(),
            product.getName(),
            product.getDescription(),
            quantity,
            product.getPrice()
        );
    }
}
