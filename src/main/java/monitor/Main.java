package monitor;

import monitor.config.EmailConfig;
import monitor.util.PropertiesLoader;

import java.util.Properties;

/**
 * Created by Basem
 */
public class Main {
    public static final String FILTER_PROPERTIESS_NAME = "filter.properties";
    public static final String MAILPROPERTIESS_NAME = "mail.properties";
    public static final String MONITOREDLOGS_PROPERTIESS_NAME = "monitoredlogs.properties";

    public static void main(String[] args) {

        PropertiesLoader loader = new PropertiesLoader();
        Properties filterProperties = loader.load(FILTER_PROPERTIESS_NAME);
        EmailConfig mailProperties = new EmailConfig(loader.load(MAILPROPERTIESS_NAME));
        Properties monitoredLogsProperties = loader.load(MONITOREDLOGS_PROPERTIESS_NAME);

        System.out.println("test");
        System.out.println(mailProperties.getSmtpPassword());




    }
}
