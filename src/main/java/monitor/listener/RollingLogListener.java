package monitor.listener;

import monitor.util.Observer;
import monitor.filter.LogFilter;
import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class RollingLogListener implements LogListener {

    Set<LogFilter> filters;
    Set<Observer> observers;

    private String fileName;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public RollingLogListener() {
        filters = new HashSet<LogFilter>();
        observers = new HashSet<Observer>();
    }

    @Override
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object event) {
        for (Observer observer : observers) {
            observer.notify(event);
        }
    }

    @Override
    public void init(Tailer tailer) {
        fileName = tailer.getFile().getName();
    }

    @Override
    public void fileNotFound() {
        logger.info("couldnt open log file: {}", fileName);
    }

    @Override
    public void fileRotated() {
        logger.info("Log file rotated: {}", fileName);
    }

    @Override
    public void handle(String line) {
        for (LogFilter filter : filters) {
            if (filter.match(line)) {
                notifyObservers(line);
            }
        }
    }

    @Override
    public void handle(Exception ex) {
        logger.error("Something went wrong", ex);
    }
}
