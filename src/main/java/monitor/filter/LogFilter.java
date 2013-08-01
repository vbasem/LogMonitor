package monitor.filter;

public interface LogFilter {
    public boolean match(String event);
}
