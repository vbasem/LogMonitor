package monitor;

import monitor.action.Action;
import monitor.action.MailAction;
import monitor.config.EmailConfig;
import monitor.filter.FilterFactory;
import monitor.filter.LogFilter;
import monitor.listener.LogListener;
import monitor.listener.RollingLogListener;
import monitor.monitoring.LogMonitorFactory;
import monitor.monitoring.Monitor;
import monitor.process.LogMonitorManagedProcess;
import monitor.process.ManagedProcess;
import monitor.util.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Basem
 */
public class Main {

    public static final String FILTER_PROPERTIESS_NAME = "filter.properties";
    public static final String MAILPROPERTIESS_NAME = "mail.properties";
    public static final String MONITOREDLOGS_PROPERTIESS_NAME = "monitoredlogs.properties";


    public static void main(String[] args) {

        ManagedProcess monitoringProcess;
        List<Monitor> monitors;
        FilterFactory filterFactory = new FilterFactory();
        LogMonitorFactory logMonitorFactory = new LogMonitorFactory();
        PropertiesLoader loader = new PropertiesLoader();
        Properties filterProperties = loader.load(FILTER_PROPERTIESS_NAME);
        EmailConfig mailProperties = new EmailConfig(loader.load(MAILPROPERTIESS_NAME));
        Properties monitoredLogsProperties = loader.load(MONITOREDLOGS_PROPERTIESS_NAME);
        Action mailAction = new MailAction(mailProperties);
        LogListener listener = new RollingLogListener();


        listener.addObserver(mailAction);
        listener.addFilters(filterFactory.create(filterProperties));


        monitors = logMonitorFactory.createTailerMonitors(monitoredLogsProperties, listener);

        monitoringProcess = new LogMonitorManagedProcess(monitors);

        Runtime.getRuntime().addShutdownHook(new Thread(monitoringProcess));

        monitoringProcess.start();
    }

}
