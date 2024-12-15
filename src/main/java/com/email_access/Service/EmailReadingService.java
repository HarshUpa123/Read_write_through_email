package com.email_access.Service;

import com.email_access.requestAndResponse.EmailRequest;
import com.email_access.requestAndResponse.EmailResponse;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SubjectTerm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
@Service
public class EmailReadingService {


    public List<EmailResponse> fetchEmail(EmailRequest emailRequest) throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imaps.host", "imap.gmail.com");
        properties.setProperty("mail.imaps.port", "993");
//        properties.put("mail.imaps.ssl.enable", "true");

        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore();
        store.connect(emailRequest.getUserEmail(), emailRequest.getAppPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        List<EmailResponse> emailResponses = new ArrayList<>();
        for (Message message : messages) {
            EmailResponse emailResponse = new EmailResponse();
            emailResponse.setFrom(Arrays.toString(message.getFrom()));
            emailResponse.setSubjects(message.getSubject());
            emailResponses.add(emailResponse);
        }
        inbox.close(false);
        store.close();
        return emailResponses;
    }
}
