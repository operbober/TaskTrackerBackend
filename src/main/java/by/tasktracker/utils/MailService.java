package by.tasktracker.utils;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Sent emails service
 *
 * Created by malets on 1/9/2017.
 */
@Service
public class MailService {

    private static final Logger LOGGER = Logger.getLogger(MailService.class.getName());

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SMTP_CUSTOM_PROPERTY_KEY = "mail.smtp.socketFactory.class";
    private static final String SMTP_CUSTOM_PROPERTY_VALUE = "javax.net.ssl.SSLSocketFactory";

    private String email;
    private String password;

    public MailService(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Sends emails with custom single receiver, subject and text in html format.
     * Call method in new tread is necessary because outlook ping is near 15-30 seconds
     * and client need successful response.
     *
     * @param receiverEmail single receiver email
     * @param subject       email subject
     * @param messageText   html-formatted text of email
     */
    public void sendEmail(String receiverEmail,
                          String subject,
                          String messageText) {
        new Thread(() -> {
            try {
                if (email == null) {
                    throw new NullPointerException();
                }
                Message message = new MimeMessage(getEmailSession());
                message.setFrom(new InternetAddress(email));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(receiverEmail));
                message.setSubject(subject);
                message.setContent(messageText, "text/html; charset=utf-8");
                LOGGER.info("Trying to send email to " + receiverEmail);
                Transport.send(message);
                LOGGER.info("Message successfully sent!");
            } catch (MessagingException | NullPointerException e) {
                LOGGER.warning(e.getMessage());
            }
        }).start();
    }

    /**
     * Current method returns session for outlook365 smtp-configuration. Authentication is embedded
     * with final static values.
     *
     * @return Outlook365 session config
     */
    private Session getEmailSession() {

        Properties props = new Properties();
        props.put(SMTP_CUSTOM_PROPERTY_KEY, SMTP_CUSTOM_PROPERTY_VALUE);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", "true");

        return Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
    }
}
