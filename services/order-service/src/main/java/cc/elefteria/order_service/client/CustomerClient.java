package cc.elefteria.order_service.client;

import cc.elefteria.order_service.record.CustomerRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
    name = "customer-service",
    path = "${application.config.customer-url}"
)
public interface CustomerClient {
  
  @GetMapping("/{id}")
  Optional<CustomerRecord> findCustomerById(@PathVariable("id") String id);
}
