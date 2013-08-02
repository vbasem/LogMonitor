package monitor.process

import monitor.monitoring.Monitor
import spock.lang.Specification

/**
 * Created by Basem
 */
class LogMonitorManagedProcessSuite extends Specification {

    def "LogMonitorManagedProcess should start all its monitors when start is called"() {
        setup:
        def monitorMock = Mock(Monitor)
        def managedProcess = new LogMonitorManagedProcess([monitorMock, monitorMock])

        when:
        managedProcess.start()

        then:
        2 * monitorMock.startMonitoring()
    }

    def "LogMonitorManagedProcess should stop all its monitors when shutdown is called"() {
        setup:
        def monitorMock = Mock(Monitor)
        def managedProcess = new LogMonitorManagedProcess([monitorMock, monitorMock])

        when:
        managedProcess.shutdown()

        then:
        2 * monitorMock.stopMonitoring()

    }

    def "LogMonitorManagedProcess calls shutdown when its thread is executed"() {
        setup:
        def managedProcess = Spy(LogMonitorManagedProcess, constructorArgs: [[]])

        when:
        managedProcess.run()

        then:
        1 * managedProcess.shutdown()
    }
}
