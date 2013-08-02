package monitor.listener;

import monitor.util.Observable;
import monitor.filter.LogFilter;
import org.apache.commons.io.input.TailerListener;

public interface LogListener extends TailerListener, Observable {

    public void addFilter(LogFilter filter);
}
