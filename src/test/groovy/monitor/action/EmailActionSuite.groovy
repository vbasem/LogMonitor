package monitor.action

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage

class EmailActionSuite {
    def mailAction
    def mailport = 25
    def recepients = ["a@b.com", "c@d.com"]

    def setup() {
        mailAction = new MailAction(mailPort, "localhost", "user", "password", )
    }

    def "MailAction should send an email to all recepients"() {
        def server = SimpleSmtpServer.start(mailport)
        def msg = "test message"

        when:
        mailAction.notify(msg)
        server.stop()

        then: server.receivedEmailSize == 2

        def emails = server.receivedEmail
        def firstEmail = (SmtpMessage) emails.next()

        then:
        firstEmail.body == msg
        firstEmail.getHeaderValue("subject")


    }
}

