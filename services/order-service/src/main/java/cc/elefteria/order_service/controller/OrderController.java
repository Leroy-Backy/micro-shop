package cc.elefteria.order_service.controller;

import cc.elefteria.order_service.record.OrderRecord;
import cc.elefteria.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  
  private final OrderService orderService;
  
  @PostMapping
  public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRecord order) {
    return ResponseEntity.ok(orderService.createOrder(order));
  }
}
