package monitor.monitoring

import monitor.listener.LogListener
import monitor.monitoring.LogMonitorFactory
import spock.lang.Specification

/**
 * Created by Basem
 */
class LogMonitorFactorySuite extends Specification {


    def "Log Monitor factory should create a TailerMonitor with given listener"() {
        setup:
        def factory = new LogMonitorFactory();
        def logFileUrl = Thread.currentThread().getContextClassLoader().getResource("test1.log")
        def msg = "test1 filter 1 test"
        def listener = Mock(LogListener)

        when:
        def monitor = factory.createTailerMonitor(new File(logFileUrl.toURI()), listener)

        monitor.startMonitoring()
        Thread.sleep(500)

        then:
        1 * listener.handle(msg)


        cleanup:
        monitor.stopMonitoring()
    }

    def "LogMonitorFactory creates a monitor for each file listed in the properties"() {
        setup:
        def factory = new LogMonitorFactory()
        def files = [new File("."), new File(".")]
        def listener = Mock(LogListener)

        when:
        def monitors = factory.createTailerMonitors(files, listener)

        then:
        monitors.size == 2
    }

    def "LogMonitorFactory creates a monitor for each entry in properties file"() {
        setup:
        def factory = new LogMonitorFactory()
        def listener = Mock(LogListener)
        def props = new Properties();
        props.setProperty("log.file1", ".");
        props.setProperty("log.file2", ".");

        when:
        def monitors = factory.createTailerMonitors(props, listener)

        then:
        monitors.size == 2
    }
}
