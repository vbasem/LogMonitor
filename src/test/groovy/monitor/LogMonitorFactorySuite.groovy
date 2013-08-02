package monitor

import monitor.action.Action
import monitor.filter.LogFilter
import monitor.listener.LogListener
import monitor.listener.RollingLogListener
import spock.lang.Specification

/**
 * Created by Basem
 */
class LogMonitorFactorySuite extends Specification {


    def "Log Monitor factory should create a TailerMonitor with given filters/observer/listener"() {
        setup:
        def factory = new LogMonitorFactory();
        def logFileUrl = Thread.currentThread().getContextClassLoader().getResource("test1.log")
        def msg = "test1"
        def listener = new RollingLogListener()
        def filterMock = Mock(LogFilter)
        def actionMock = Mock(Action)

        when:
        def monitor = factory.createTailerMonitor(new File(logFileUrl.toURI()), listener, Arrays.asList(filterMock), Arrays.asList(actionMock))

        monitor.startMonitoring()
        Thread.sleep(500)

        then:
        1 * filterMock.match(msg) >> true
        1 * actionMock.notify(msg)


        cleanup:
        monitor.stopMonitoring()
    }

    def "LogMonitorFactory creates a monitor for each file listed in the properties"() {
        setup:
        def factory = new LogMonitorFactory()

    }
}
