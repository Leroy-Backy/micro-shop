package cc.elefteria.customer_service.controller;

import cc.elefteria.customer_service.record.CustomerRecord;
import cc.elefteria.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
  
  private final CustomerService customerService;
  
  @PostMapping
  public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRecord customer) {
    return ResponseEntity.ok(customerService.createCustomer(customer));
  }
  
  @PutMapping
  public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRecord customer) {
    customerService.updateCustomer(customer);
    return ResponseEntity.accepted().build();
  }
  
  @GetMapping
  public ResponseEntity<List<CustomerRecord>> findAll() {
    return ResponseEntity.ok(customerService.getAll());
  }
  
  @GetMapping("/exists/{id}")
  public ResponseEntity<Boolean> existsById(@PathVariable("id") String id) {
    return ResponseEntity.ok(customerService.existsById(id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerRecord> findById(@PathVariable("id") String id) {
    return ResponseEntity.ok(customerService.findById(id));
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
    customerService.deleteById(id);
    return ResponseEntity.accepted().build();
  }
}
