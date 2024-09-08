package cc.elefteria.order_service.entity;

import cc.elefteria.order_service.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String reference;
  private BigDecimal totalAmount;
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;
  private String customerId;
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private Set<OrderLine> orderLines = new HashSet<>();
  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private Timestamp createdAt;
  @UpdateTimestamp
  @Column(insertable = false)
  private Timestamp updatedAt;
}
