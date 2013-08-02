package monitor.monitoring;

import monitor.listener.LogListener;
import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Basem
 */
public class LogMonitorFactory {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Monitor createTailerMonitor(File logFile, LogListener listener) {

        Monitor monitor = new TailerMonitor(new Tailer(logFile, listener));

        return monitor;
    }

    public List<Monitor> createTailerMonitors(List<File> files, LogListener listener) {
        List<Monitor> monitors = new ArrayList<Monitor>();

        for (File file : files) {
            monitors.add(createTailerMonitor(file, listener));
        }

        return monitors;
    }

    public List<Monitor> createTailerMonitors(Properties filesProps, LogListener listener) {
        List<File> files = new ArrayList<File>();

        for (Object filePath : filesProps.values()) {
            File f = new File(String.valueOf(filePath));
            if (f.exists()) {
                files.add(f);
            } else {
                logger.error("Cant create file for : {} ", filePath);
            }
        }

        return createTailerMonitors(files, listener);

    }

}
