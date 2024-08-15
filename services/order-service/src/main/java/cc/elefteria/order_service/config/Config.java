package cc.elefteria.order_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.config")
@Data
public class Config {
  private String productUrl;
  private String customerUrl;
  private String paymentUrl;
}
