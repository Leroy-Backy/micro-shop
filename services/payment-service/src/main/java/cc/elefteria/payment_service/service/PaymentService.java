package cc.elefteria.payment_service.service;

import cc.elefteria.payment_service.kafka.NotificationProducer;
import cc.elefteria.payment_service.kafka.PaymentNotificationEvent;
import cc.elefteria.payment_service.records.PaymentRecord;
import cc.elefteria.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
  
  private final PaymentRepository paymentRepository;
  private final NotificationProducer notificationProducer;
  
  public Integer createPayment(PaymentRecord paymentRecord) {
    var payment = paymentRepository.save(paymentRecord.toPayment());
    
    notificationProducer.sendPaymentNotification(new PaymentNotificationEvent(
        paymentRecord.orderReference(),
        paymentRecord.amount(),
        paymentRecord.paymentMethod(),
        paymentRecord.customer().firstName(),
        paymentRecord.customer().lastName(),
        paymentRecord.customer().email()
    ));
    
    return payment.getId();
  }
}
