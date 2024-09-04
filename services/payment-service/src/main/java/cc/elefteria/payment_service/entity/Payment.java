package cc.elefteria.payment_service.entity;

import cc.elefteria.payment_service.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {
  
  @Id
  @GeneratedValue
  private Integer id;
  private BigDecimal amount;
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;
  private Integer orderId;
  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private Timestamp createdAt;
  @UpdateTimestamp
  @Column(insertable = false)
  private Timestamp updatedAt;
}
