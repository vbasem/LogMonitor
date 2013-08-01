package monitor.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Basem
 */
public class FilterFactory {

    List<LogFilter> filters;

    public FilterFactory() {
        filters = new ArrayList<LogFilter>();
    }

    public List<LogFilter> create(Properties filterConfig) {
        for (Map.Entry<Object, Object> filter : filterConfig.entrySet()) {
            filters.add(new StringBasedLogFilter((String) filter.getValue()));
        }

        return filters;
    }

}
