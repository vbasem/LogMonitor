package monitor.process;

import monitor.monitoring.Monitor;

import java.util.List;

/**
 * Created by Basem
 */
public class LogMonitorManagedProcess extends ManagedProcess {

    List<Monitor> monitors;

    public LogMonitorManagedProcess(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    @Override
    public void start() {
        for (Monitor monitor : monitors) {
            monitor.startMonitoring();
        }
    }

    @Override
    public void shutdown() {
        for (Monitor monitor : monitors) {
            monitor.stopMonitoring();
        }
    }
}
