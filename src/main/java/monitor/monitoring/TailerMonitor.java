package monitor.monitoring;

import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Basem
 */
public class TailerMonitor implements Monitor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Tailer tailer;
    Thread monitorThread;

    public TailerMonitor(Tailer tailer) {
        this.tailer = tailer;
    }

    @Override
    public void startMonitoring() {
        monitorThread = new Thread(tailer);
        monitorThread.start();
        logger.info("starting tailer for file: {}", tailer.getFile().getName());

    }

    @Override
    public void stopMonitoring() {
        logger.info("stopping tailer for file: {}", tailer.getFile().getName());
        tailer.stop();
    }
}
