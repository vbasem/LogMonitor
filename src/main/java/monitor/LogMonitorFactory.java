package monitor;

import monitor.Monitor;
import monitor.TailerMonitor;
import monitor.action.Action;
import monitor.filter.LogFilter;
import monitor.listener.LogListener;
import org.apache.commons.io.input.Tailer;

import java.io.File;
import java.util.List;

/**
 * Created by Basem
 */
public class LogMonitorFactory {

    public Monitor createTailerMonitor(File logFile, LogListener listener, List<LogFilter> filters, List<Action> actions) {

        for (LogFilter filter : filters) {
            listener.addFilter(filter);
        }

        for (Action action : actions) {
            listener.addObserver(action);
        }

        Monitor monitor = new TailerMonitor(new Tailer(logFile, listener));

        return monitor;
    }
}
