package cc.elefteria.order_service.client;

import cc.elefteria.order_service.record.PaymentRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "payment-service",
    path = "${application.config.payment-url}"
)
public interface PaymentClient {
  
  @PostMapping
  Integer requestOrderPayment(@RequestBody PaymentRecord payment);
}
