package com.colinlo.joblisting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${backend.base.url}")
    private String hostUrl;

    @Async
    public void sendConfirmationEmail(String to, String firstname, String token) {
        try {
            Context context = new Context();
            String activationLink = hostUrl + "/api/v1/registration/confirm?token=" + token;
            context.setVariable("activationLink", activationLink);
            context.setVariable("firstname", firstname);

            String htmlContent = templateEngine.process("confirmationEmail", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setFrom("${spring.mail.username}");
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setText(htmlContent, true);
            ClassPathResource springImageResource = new ClassPathResource("static/images/springboot.png");
            ClassPathResource vueImageResource = new ClassPathResource("static/images/vuejs.png");
            ClassPathResource mongoImageResource = new ClassPathResource("static/images/mongodb.png");
            helper.addInline("springboot", springImageResource);
            helper.addInline("vuejs", vueImageResource);
            helper.addInline("mongodb", mongoImageResource);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}
