package monitor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Basem
 */
public class PropertiesLoader {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Properties load(String resourceName) {
        Properties p = new Properties();
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName));
        } catch (IOException e) {
            logger.error("Cant load properties for : {} ", resourceName);
        }

        return p;
    }
}
