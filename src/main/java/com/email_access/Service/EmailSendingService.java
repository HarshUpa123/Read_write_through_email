package com.email_access.Service;

import com.email_access.requestAndResponse.EmailRequest;
import com.email_access.requestAndResponse.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailSendingService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String userName;

    @Value("${spring.mail.password}")
    public String password;

    public String sendEmail(SendEmailRequest sendEmailRequest) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(userName);
            simpleMailMessage.setTo(sendEmailRequest.getRecipientEmail());
            simpleMailMessage.setSubject(sendEmailRequest.getSubject());
            simpleMailMessage.setText(sendEmailRequest.getBody());

            javaMailSender.send(simpleMailMessage);
            return "mail send successfully ";
    }
}