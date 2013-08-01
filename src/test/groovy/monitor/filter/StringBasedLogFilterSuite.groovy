package monitor.filter

import spock.lang.Specification

/**
 * Created by Basem
 */
class StringBasedLogFilterSuite extends Specification {

    def "String based log filter should return true if the event contains target string"() {
        def stringLogFilter = new StringBasedLogFilter("INFO i should be present")
        def event = "01/01/01 00:00:00 some log INFO i should be present bbla la blallbllblas //"

        when:
        def result = stringLogFilter.match(event)

        then:
        result == true
    }

    def "String based log filter should return false if the event does not contain target string"() {
        def stringLogFilter = new StringBasedLogFilter("INFO i should be present")
        def event = "01/01/01 00:00:00 some log INFO nothing to do here"

        when:
        def result = stringLogFilter.match(event)

        then:
        result == false
    }
}
