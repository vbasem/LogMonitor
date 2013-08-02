package monitor.monitoring;

import org.apache.commons.io.input.Tailer;

/**
 * Created by Basem
 */
public class TailerMonitor implements Monitor {

    Tailer tailer;
    Thread monitorThread;

    public TailerMonitor(Tailer tailer) {
        this.tailer = tailer;
    }

    @Override
    public void startMonitoring() {
        monitorThread = new Thread(tailer);
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    @Override
    public void stopMonitoring() {
        tailer.stop();
    }
}
