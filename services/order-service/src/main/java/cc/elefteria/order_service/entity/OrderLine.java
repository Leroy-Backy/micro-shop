package cc.elefteria.order_service.entity;

import jakarta.persistence.*;
import lombok.*;

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
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Order order;
  private Integer productId;
  private double quantity;
}
