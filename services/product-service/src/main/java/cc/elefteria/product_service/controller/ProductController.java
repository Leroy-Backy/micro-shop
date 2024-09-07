package cc.elefteria.product_service.controller;

import cc.elefteria.product_service.record.ProductRecord;
import cc.elefteria.product_service.record.PurchaseRequest;
import cc.elefteria.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
  
  private final ProductService productService;
  
  @PostMapping
  public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRecord product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }
  
  @PostMapping("/purchase")
  public ResponseEntity<List<ProductRecord>> purchaseProducts(@RequestBody List<PurchaseRequest> request) {
    return ResponseEntity.ok(productService.purchaseProducts(request));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ProductRecord> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(productService.findById(id));
  }
  
  @GetMapping
  public ResponseEntity<List<ProductRecord>> findAll() {
    return ResponseEntity.ok(productService.getAll());
  }
}
