package monitor.filter

import spock.lang.Specification

/**
 * Created by Basem
 */
class FilterFactorySuite extends Specification {
    def filterconfig

    def setup() {
        filterconfig = new Properties();
        filterconfig.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("testfilter.properties"))
    }
    def "FilterInitializer should create a LogFilter object for each entry"() {
        def factory = new FilterFactory()

        when:
        def filters = factory.create(filterconfig)

        then:
        filters.size == 2
        filters.get(0).match("this filter should return true for filter 2 test") == true
        filters.get(1).match("this filter cointains filter 1 test in the middle") == true
    }
}
