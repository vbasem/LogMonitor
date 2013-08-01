package monitor

import spock.lang.Specification

class LogMonitorSuite extends Specification {

    def monitor
    def filter
    def filter2
    def observer
    def observer2

    def setup() {
       monitor = new RollingLogMonitor()
       filter = Mock(LogFilter)
       filter2 = Mock(LogFilter)
       observer = Mock(Observer)
       observer2 = Mock(Observer)
    }

    def "Log monitor passes each event to filters"() {
        def msg = "test log event"

        monitor.addFilter(filter)
        monitor.addFilter(filter2)

        when:
        monitor.handle(msg)

        then:
        1 * filter.match(msg) >> true
        1 * filter2.match(msg) >> true
    }

    def "Log monitor notifies observers when filter returns true"() {
        def msg = "test"

        monitor.addFilter(filter)
        monitor.addObserver(observer)
        monitor.addObserver(observer2)

        when:
        monitor.handle(msg)

        then:
        1 * filter.match(msg) >> true
        1 * observer.notify(msg)
        1 * observer2.notify(msg)
    }

    def "Log monitor does not notify observer if filter returns false"() {
        def msg = "test"

        monitor.addFilter(filter)
        monitor.addObserver(observer)

        filter.match(msg) >> false

        when:
        monitor.handle(msg)

        then:
        observer.notify(msg) >> {throw new IllegalAccessError("observer should not be notified when filter returns false")}

    }


}