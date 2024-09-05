package cc.elefteria.order_service.service;

import cc.elefteria.order_service.client.CustomerClient;
import cc.elefteria.order_service.client.PaymentClient;
import cc.elefteria.order_service.client.ProductClient;
import cc.elefteria.order_service.entity.Order;
import cc.elefteria.order_service.entity.OrderLine;
import cc.elefteria.order_service.exception.BusinessException;
import cc.elefteria.order_service.kafka.OrderConfirmationEvent;
import cc.elefteria.order_service.kafka.OrderProducer;
import cc.elefteria.order_service.record.*;
import cc.elefteria.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
  
  private final OrderRepository orderRepository;
  private final CustomerClient customerClient;
  private final ProductClient productClient;
  private final OrderProducer orderProducer;
  private final PaymentClient paymentClient;

  public Integer createOrder(OrderRecord order) {
    // check the customer -> customer-service (OpenFeign)
    CustomerRecord customer = customerClient.findCustomerById(order.customerId())
        .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exist with id: " + order.customerId()));
    
    // purchase products --> product-service (RestTemplate)
    List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(order.products());
    
    Order orderToPersist = order.toOrder();
    
    // add order lines
    for (PurchaseRequest purchaseRequest: order.products()) {
      //todo check saving
      OrderLine orderLine = OrderLine.builder()
          .order(orderToPersist)
          .productId(purchaseRequest.productId())
          .quantity(purchaseRequest.quantity())
          .build();
      orderToPersist.getOrderLines().add(orderLine);
    }

    // persist order
    orderRepository.save(orderToPersist);
    
    paymentClient.requestOrderPayment(new PaymentRecord(
        null,
        orderToPersist.getTotalAmount(),
        orderToPersist.getPaymentMethod(),
        orderToPersist.getId(),
        orderToPersist.getReference(),
        customer
    ));
    
    orderProducer.sendOrderConfirmation(
        new OrderConfirmationEvent(
            orderToPersist.getReference(),
            orderToPersist.getTotalAmount(),
            orderToPersist.getPaymentMethod(),
            customer,
            purchasedProducts
        )
    );
    
    return orderToPersist.getId();
  }

  public List<OrderRecord> findAll() {
    return orderRepository.findAll().stream().map(OrderRecord::fromOrder).toList();
  }

  public OrderRecord findById(Integer id) {
    return orderRepository.findById(id)
        .map(OrderRecord::fromOrder)
        .orElseThrow(() -> new EntityNotFoundException("Cannot find order with id: " + id));
  }
}
