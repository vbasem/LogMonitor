package monitor.util;

public interface Observable {

    public void addObserver(Observer observer);
    public void notifyObservers(Object event);
}
