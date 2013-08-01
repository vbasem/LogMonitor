package monitor.action

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import monitor.config.EmailConfig
import spock.lang.Shared
import spock.lang.Specification

class EmailActionSuite extends Specification {

    @Shared
    EmailConfig mailConfig

    def mailAction

    def server
    def mailport = 25

    def setupSpec() {
        mailConfig = new EmailConfig("test_mail.properties")
    }

    def setup() {
        mailAction = new MailAction(mailConfig)
    }

    def "MailAction should send an email to all recepients"() {
        server = SimpleSmtpServer.start(mailport)
        def msg = "test message"

        when:
        mailAction.notify(msg)
        server.stop()

        then: server.receivedEmailSize == 1

        def emails = server.receivedEmail
        def firstEmail = (SmtpMessage) emails.next()

        then:
        msg  == firstEmail.getBody()
        mailConfig.getTopic() == firstEmail.getHeaderValue("Subject")
        // a space is added between each recipients email address, lets remove it
        mailConfig.getRecipients() == firstEmail.getHeaderValue("To").replace(' ', '')
        mailConfig.getSender() == firstEmail.getHeaderValue("From")

    }

}

