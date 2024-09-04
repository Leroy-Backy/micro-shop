package cc.elefteria.payment_service.repository;

import cc.elefteria.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
