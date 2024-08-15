package cc.elefteria.order_service.client;

import cc.elefteria.order_service.config.Config;
import cc.elefteria.order_service.exception.BusinessException;
import cc.elefteria.order_service.record.PurchaseRequest;
import cc.elefteria.order_service.record.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
  
//  @Value("${application.config.product-url}")
//  private String productUrl;
  private final Config config;
  private final RestTemplate restTemplate;
  
  public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);
    
    ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
    
    ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
        config.getProductUrl() + "/purchase",
        HttpMethod.POST,
        requestEntity,
        responseType
    );
    
    if (responseEntity.getStatusCode().isError()) {
      throw new BusinessException("An error occurred while processing the products purchase:: " + responseEntity.getStatusCode().value());
    }
    
    return responseEntity.getBody();
  }
}
