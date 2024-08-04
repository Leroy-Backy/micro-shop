package cc.elefteria.customer_service.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
  @NotNull(message = "Street name is required")
  private String street;
  private String houseNumber;
  private String zipCode;
}
