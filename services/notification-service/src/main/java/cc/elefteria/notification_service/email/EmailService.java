package cc.elefteria.notification_service.email;

import cc.elefteria.notification_service.kafka.order.OrderConfirmationEvent;
import cc.elefteria.notification_service.kafka.order.PurchaseResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class EmailService {
  
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;
  
  @Async
  public void sendPaymentSuccessEmail(
      String destinationEmail,
      String customerName,
      BigDecimal amount,
      String orderReference
  ) throws MessagingException {
    Context context = new Context();
    context.setVariable("customerName", customerName);
    context.setVariable("amount", amount);
    context.setVariable("orderReference", orderReference);

    sendEmail(destinationEmail, EmailTemplate.PAYMENT_CONFIRMATION, context);
  }

  @Async
  public void sendOrderSuccessEmail(
      String destinationEmail,
      String customerName,
      BigDecimal amount,
      String orderReference,
      List<PurchaseResponse> products
  ) throws MessagingException {
    Context context = new Context();
    context.setVariable("customerName", customerName);
    context.setVariable("amount", amount);
    context.setVariable("orderReference", orderReference);
    context.setVariable("products", products);

    sendEmail(destinationEmail, EmailTemplate.ORDER_CONFIRMATION, context);
  }
  
  @Async
  public void sendEmail(
      String destinationEmail, 
      EmailTemplate emailTemplate, 
      Context context
  ) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
        mimeMessage,
        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        StandardCharsets.UTF_8.name()
    );

    final String templateName = emailTemplate.getTemplateName();
    String htmlTemplate = templateEngine.process(templateName, context);

    mimeMessageHelper.setSubject(emailTemplate.getSubject());
    mimeMessageHelper.setText(htmlTemplate, true);
    mimeMessageHelper.setFrom("somemail@bruh.com");
    mimeMessageHelper.setTo(destinationEmail);

    try {
      mailSender.send(mimeMessage);
      log.info("INFO - Email sent to {}", destinationEmail);
    } catch (MailException e) {
      log.warn("WARNING - Cannot send email to {}", destinationEmail);
    }
  }
}
