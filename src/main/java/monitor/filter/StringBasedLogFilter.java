package monitor.filter;

/**
 * Created by Basem
 */
public class StringBasedLogFilter implements LogFilter {

    String filter;

    public StringBasedLogFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean match(String event) {
        return event.contains(filter);
    }
}
