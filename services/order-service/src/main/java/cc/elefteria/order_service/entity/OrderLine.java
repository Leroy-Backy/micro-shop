package cc.elefteria.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class OrderLine {

  @Id
  @GeneratedValue
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  private Integer productId;
  private double quantity;
}
