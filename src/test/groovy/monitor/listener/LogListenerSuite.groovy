package monitor.listener

import monitor.filter.LogFilter
import spock.lang.Specification

class LogListenerSuite extends Specification {

    def listener
    def filter
    def filter2
    def observer
    def observer2

    def setup() {
       listener = new RollingLogListener()
       filter = Mock(LogFilter)
       filter2 = Mock(LogFilter)
       observer = Mock(monitor.util.Observer)
       observer2 = Mock(monitor.util.Observer)
    }

    def "Log Listener passes each event to filters"() {
        def msg = "test log event"

        listener.addFilter(filter)
        listener.addFilter(filter2)

        when:
        listener.handle(msg)

        then:
        1 * filter.match(msg) >> true
        1 * filter2.match(msg) >> true
    }

    def "Log Listener notifies observers when filter returns true"() {
        def msg = "test"

        listener.addFilter(filter)
        listener.addObserver(observer)
        listener.addObserver(observer2)

        when:
        listener.handle(msg)

        then:
        1 * filter.match(msg) >> true
        1 * observer.notify(msg)
        1 * observer2.notify(msg)
    }

    def "Log Listener does not notify observer if filter returns false"() {
        def msg = "test"

        listener.addFilter(filter)
        listener.addObserver(observer)

        filter.match(msg) >> false

        when:
        listener.handle(msg)

        then:
        observer.notify(msg) >> {throw new IllegalAccessError("observer should not be notified when filter returns false")}

    }

    def "LogListener registers all filters when addfilters is called"() {
        listener.addFilters([filter, filter2])

        when:
        listener.handle("")

        then:
        1 * filter.match("")
        1 * filter2.match("")

    }




}