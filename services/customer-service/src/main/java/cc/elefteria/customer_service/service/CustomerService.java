package cc.elefteria.customer_service.service;

import cc.elefteria.customer_service.entity.Customer;
import cc.elefteria.customer_service.exception.CustomerNotFoundException;
import cc.elefteria.customer_service.record.CustomerRecord;
import cc.elefteria.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
  
  private final CustomerRepository customerRepository;
  
  public String createCustomer(CustomerRecord customerRecord) {
    if (customerRecord == null) return null;
    
    Customer customer = Customer.builder()
        .firstName(customerRecord.firstName())
        .lastName(customerRecord.lastName())
        .email(customerRecord.email())
        .address(customerRecord.address())
        .build();
    
    customerRepository.save(customer);
    
    return customer.getId();
  }

  public void updateCustomer(CustomerRecord customerRecord) {
    if (customerRecord == null || customerRecord.id() == null) return;//throw exception in general, but im lazy
    
    Customer customer = customerRepository.findById(customerRecord.id()).orElseThrow(() -> new CustomerNotFoundException(
        String.format("Cannot update customer:: No customer found with id: %s", customerRecord.id()))
    );
    customerRecord.mergeWithCustomer(customer);
    customerRepository.save(customer);
  }
  
  public List<CustomerRecord> getAll() {
    return customerRepository.findAll().stream().map(CustomerRecord::fromCustomer).toList();
  }
  
  public boolean existsById(String id) {
    return customerRepository.existsById(id);
  }
  
  public CustomerRecord findById(String id) {
    return customerRepository.findById(id)
        .map(CustomerRecord::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Cannot find customer:: No customer found with id: %s", id))
        );
  }
  
  public void deleteById(String id) {
    customerRepository.deleteById(id);
  }
}
