package com.politechnika.housing.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailConfig {

    private static Properties properties;
    private static Session session;
    private static final String passwordMail = "infernus3";



    public static void configure() {
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dev312.test@gmail.com", passwordMail);
            }
        });
    }

    public static void sendMail(String email, String firstName, String lastName, String password, String token) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dev312.test@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Activation");
            message.setText(firstName+""+lastName + " "  + password + "   http://localhost:3000/activation/" + token);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
