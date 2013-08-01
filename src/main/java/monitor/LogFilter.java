package monitor;

public interface LogFilter {

    public boolean match(String event);
}
