package com.imad.springAuthServer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    public void sendCredentialsEmail(String toEmail, String username, String password) {
        // Prepare the evaluation context
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("password", password);

        // Process the HTML template
        String htmlContent = templateEngine.process("credentials-email", context);

        // Create the email
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Your Account Credentials");
            helper.setText(htmlContent, true); // Set to true to send HTML
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to create email", e);
        }

        // Send the email
        mailSender.send(message);
    }
}
