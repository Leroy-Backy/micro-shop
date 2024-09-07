package cc.elefteria.order_service.controller;

import cc.elefteria.order_service.record.OrderRecord;
import cc.elefteria.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  
  private final OrderService orderService;
  
  @PostMapping
  public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRecord order) {
    return ResponseEntity.ok(orderService.createOrder(order));
  }
  
  @GetMapping
  public ResponseEntity<List<OrderRecord>> findAll() {
    return ResponseEntity.ok(orderService.findAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<OrderRecord> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(orderService.findById(id));
  }
}
