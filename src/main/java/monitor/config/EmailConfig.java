package monitor.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailConfig extends Properties {
    public static final String SMTP_HOST = "mail.smtp.host";
    public static final String SMTP_PORT = "mail.smtp.port";
    public static final String SMTP_AUTHENTICATED = "mail.smtp.auth";
    public static final String SMTP_USER = "mail.user";
    public static final String SMTP_PASS = "mail.password";
    public static final String MAIL_TOPIC = "mail.topic";
    public static final String MAIL_SENDER = "mail.sender";
    public static final String MAIL_RECIPIENTS = "mail.recipients";

    public EmailConfig(String configFileName) {
        super();
        try {
            this.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public EmailConfig(Properties config) {
        super(config);
    }

    public String getSmtpHost() {
        return getProperty(SMTP_HOST);
    }

    public int getSmtpPort() {
        return Integer.parseInt(getProperty(SMTP_PORT));
    }

    public String getSmtpUser() {
        return getProperty(SMTP_USER);
    }

    public String getSmtpPassword() {
        return getProperty(SMTP_PASS);
    }

    public String getTopic() {
        return getProperty(MAIL_TOPIC);
    }

    public String getSender() {
        return getProperty(MAIL_SENDER);
    }

    public String getRecipients() {
        return getProperty(MAIL_RECIPIENTS);
    }

    public Boolean isAuthenticated() {
        return Boolean.valueOf(getProperty("mail.smtp.auth"));
    }
}
