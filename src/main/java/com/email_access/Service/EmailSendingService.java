package com.email_access.Service;

import com.email_access.requestAndResponse.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
            if(sendEmailRequest.getBcc() !=null){
            simpleMailMessage.setBcc(sendEmailRequest.getBcc());}
            if(sendEmailRequest.getCc() !=null){
                simpleMailMessage.setCc(sendEmailRequest.getCc());}
            simpleMailMessage.setText(sendEmailRequest.getBody());

            javaMailSender.send(simpleMailMessage);
            return "mail send successfully ";
    }
}