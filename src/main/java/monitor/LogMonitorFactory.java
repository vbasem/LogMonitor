package monitor;

import monitor.Monitor;
import monitor.TailerMonitor;
import monitor.action.Action;
import monitor.filter.LogFilter;
import monitor.listener.LogListener;
import org.apache.commons.io.input.Tailer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Basem
 */
public class LogMonitorFactory {

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

}
