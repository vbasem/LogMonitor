package monitor.action;

import monitor.config.EmailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailAction implements Action{

    transient Logger logger = LoggerFactory.getLogger(this.getClass());

    Session mailSession;
    EmailConfig config;

    public MailAction(EmailConfig config) {
        this.config = config;
        mailSession = Session.getDefaultInstance(config);

    }

    protected void send(String body) {
        MimeMessage email = new MimeMessage(mailSession);
        try {
            email.setSubject(config.getTopic());
            email.setText(body);
            email.setFrom(new InternetAddress(config.getSender()));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(config.getRecipients()));
            Transport.send(email);

            logger.info("Sent email for event: {} - using config: {}", body, config);
        } catch (MessagingException e) {
            logger.error("Failed to created email!", e);
        }
    }


    @Override
    public void notify(Object event) {
        send((String) event);
    }
}
