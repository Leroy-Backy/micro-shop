package cc.elefteria.product_service.service;

import cc.elefteria.product_service.entity.Category;
import cc.elefteria.product_service.entity.Product;
import cc.elefteria.product_service.exception.ProductPurchaseException;
import cc.elefteria.product_service.record.ProductRecord;
import cc.elefteria.product_service.record.PurchaseRequest;
import cc.elefteria.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  
  private final ProductRepository productRepository;


  public Integer createProduct(ProductRecord productRecord) {
    if (productRecord == null) return null;

    Product product = Product.builder()
        .name(productRecord.name())
        .description(productRecord.description())
        .price(productRecord.price())
        .quantity(productRecord.quantity())
        .category(
            Category.builder()
                .id(productRecord.categoryId())
                .build()
        )
        .build();
    
    return productRepository.save(product).getId();
  }

  @Transactional
  public List<ProductRecord> purchaseProducts(List<PurchaseRequest> request) {
    var productIds = request.stream().map(PurchaseRequest::productId)
        .toList();
    
    var storedProducts = productRepository.findAllByIdInOrderById(productIds);
    
    if (productIds.size() != storedProducts.size()) {
      throw new ProductPurchaseException("One or more products does not exists");
    }
    
    request.sort(Comparator.comparing(PurchaseRequest::productId));
    
    var purchasedProducts = new ArrayList<ProductRecord>();
    
    for (int i = 0; i < storedProducts.size(); i++) {
      var product = storedProducts.get(i);
      var productRequest = request.get(i);
      if (product.getQuantity() < productRequest.quantity()) {
        throw new ProductPurchaseException("Insufficient stock quantity for product with id " + product.getId());
      }
      product.setQuantity(product.getQuantity() - productRequest.quantity());
      productRepository.save(product);
      purchasedProducts.add(ProductRecord.fromProductPurchase(product, productRequest.quantity()));
    }
    
    return purchasedProducts;
  }

  public ProductRecord findById(Integer id) {
    return productRepository.findById(id)
        .map(ProductRecord::fromProduct)
        .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
  }

  public List<ProductRecord> getAll() {
    return productRepository.findAll()
        .stream().map(ProductRecord::fromProduct)
        .toList();
  }
}
