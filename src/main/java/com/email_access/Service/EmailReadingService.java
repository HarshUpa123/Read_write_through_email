package com.email_access.Service;

import com.email_access.requestAndResponse.EmailRequest;
import com.email_access.requestAndResponse.EmailResponse;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
@Service
public class EmailReadingService {


    public List<EmailResponse> fetchEmail(EmailRequest emailRequest) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imaps.host", "imap.gmail.com");
        properties.setProperty("mail.imaps.port", "993");

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
            emailResponse.setDate(message.getReceivedDate());
//            String bodyText = getTextFromMessage(message);
//            String plainText = Jsoup.parse(bodyText).text();
//            emailResponse.setBodyText(bodyText);
            emailResponses.add(emailResponse);
        }
        inbox.close(false);
        store.close();
        return emailResponses;
    }

    private String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    // Method to extract text from a multipart message
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                // If you prefer HTML over plain text
                String html = (String) bodyPart.getContent();
                result.append(org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }
}
