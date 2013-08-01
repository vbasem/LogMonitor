package monitor;

import monitor.filter.LogFilter;
import org.apache.commons.io.input.TailerListener;

public interface LogMonitor extends TailerListener, Observable {

    public void addFilter(LogFilter filter);
}
