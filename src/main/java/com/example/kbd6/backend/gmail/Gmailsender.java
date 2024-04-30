package com.example.kbd6.backend.gmail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Properties;

// Below code is refered from the youtube video. because this code is also predefined so we need to write the correct
// https://www.youtube.com/watch?v=hm23MfVnkCU&t=1358s&ab_channel=LearnCodeWithDurgesh
@Service
public class Gmailsender {

    public boolean sendGmail(String to, String from, String subject, String text, File file){
        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        String username ="kunaldhavle9";
        String password = "gpbchyyulwpchwmi";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            MimeBodyPart part1 = new MimeBodyPart();
            part1.setContent(text,"text/html");
            MimeBodyPart part2 = new MimeBodyPart();
            part2.attachFile(file);
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(part1);
            multipart.addBodyPart(part2);
            message.setContent(multipart);
            Transport.send(message);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  flag;
    }
}
