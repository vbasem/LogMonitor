package monitor.util

import spock.lang.Specification

/**
 * Created by Basem
 */
class PropertiesLoaderSuite extends Specification {

/*
    filter.string.test1=filter 1 test
    filter.string.test2=filter 2 test
 */

    def "PropertiesLoader returns Properties object loaded with the resouce file name"() {
        def loader = new PropertiesLoader()


        when:
        def prop = loader.load("test_filter.properties")

        then:
        "filter 1 test" == prop.getProperty("filter.string.test1")
        "filter 2 test" == prop.getProperty("filter.string.test2")
    }
}
