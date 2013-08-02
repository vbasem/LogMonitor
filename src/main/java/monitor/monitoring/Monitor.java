package monitor.monitoring;

import monitor.filter.LogFilter;
import monitor.util.Observer;

import java.util.List;

/**
 * Created by Basem
 */
public interface Monitor {

    public void startMonitoring();
    public void stopMonitoring();
}
