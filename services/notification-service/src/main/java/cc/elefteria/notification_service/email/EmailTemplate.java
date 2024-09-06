package cc.elefteria.notification_service.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailTemplate {
  PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed."), 
  ORDER_CONFIRMATION("order-confirmation.html", "Order confirmed.");
  
  private String templateName;
  private String subject;
}
