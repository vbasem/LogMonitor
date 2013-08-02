package monitor.listener;

import monitor.util.Observable;
import monitor.filter.LogFilter;
import org.apache.commons.io.input.TailerListener;

import java.util.List;

public interface LogListener extends TailerListener, Observable {

    public void addFilter(LogFilter filter);
    public void addFilters(List<LogFilter> filters);
}
