package monitor.monitoring

import monitor.monitoring.TailerMonitor
import org.apache.commons.io.input.Tailer
import spock.lang.Specification

/**
 * Created by Basem
 */
class TailerMonitorSuite extends Specification {

    def "A monitor should start the monitoring process when start is called"() {
        def mockTailer = Mock(Tailer)
        def monitor = new TailerMonitor(mockTailer)

        mockTailer.getFile() >> Stub(File)

        when:
        monitor.startMonitoring()
        Thread.sleep(500)

        then:
        1 * mockTailer.run()

        cleanup:
        monitor.stopMonitoring()

    }
    def "A Monitor should stop all its contained monitor processes when stop is called"() {
        def mockTailer = Mock(Tailer)
        def monitor = new TailerMonitor(mockTailer)

        mockTailer.getFile() >> Stub(File)

        when:
        monitor.stopMonitoring()

        then:
        1 * mockTailer.stop()
    }
}
