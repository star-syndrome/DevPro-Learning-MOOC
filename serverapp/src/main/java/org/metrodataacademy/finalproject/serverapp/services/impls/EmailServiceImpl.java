package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ReceiptRequest;
import org.metrodataacademy.finalproject.serverapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendReceipt(ReceiptRequest receiptRequest) throws MessagingException{
        try {
            log.info("Trying sent an email to {}", receiptRequest.getRecipient());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setTo(receiptRequest.getRecipient());
            mimeMessageHelper.setSubject(receiptRequest.getSubject());

            Context thymeleafContext = getContext(receiptRequest);
            String htmlBody = templateEngine.process("receipt.html", thymeleafContext);

            mimeMessageHelper.setText(htmlBody, true);

            javaMailSender.send(mimeMessage);
            log.info("Email sent successfully to {}", receiptRequest.getRecipient());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    private Context getContext(ReceiptRequest receiptRequest) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("orderId", receiptRequest.getOrderId());
        thymeleafContext.setVariable("name", receiptRequest.getName());
        thymeleafContext.setVariable("title", receiptRequest.getTitle());
        thymeleafContext.setVariable("duration", receiptRequest.getDuration() + " minutes");
        thymeleafContext.setVariable("price", receiptRequest.getPrice());
        thymeleafContext.setVariable("orderTime", receiptRequest.getOrderTime());
        thymeleafContext.setVariable("paymentMethod", receiptRequest.getPaymentMethod());
        thymeleafContext.setVariable("paid", receiptRequest.getPaid());
        thymeleafContext.setVariable("recipient", receiptRequest.getRecipient());
        return thymeleafContext;
    }
}