package com.email_access.Controller;


import com.email_access.Service.EmailReadingService;
import com.email_access.Service.EmailSendingService;
import com.email_access.requestAndResponse.EmailRequest;
import com.email_access.requestAndResponse.EmailResponse;
import com.email_access.requestAndResponse.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailReadingService emailReadingService;
    @Autowired
    private EmailSendingService emailSendingService;

    @GetMapping("/fetch-email")
    public List<EmailResponse> fetchEmail(@RequestBody EmailRequest emailRequest) throws Exception {
       return emailReadingService.fetchEmail(emailRequest);
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody SendEmailRequest sendEmailRequest){
        return emailSendingService.sendEmail(sendEmailRequest);
    }

}
