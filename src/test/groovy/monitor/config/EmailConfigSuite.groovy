package monitor.config

import spock.lang.Specification

class EmailConfigSuite extends Specification {

    def "EmailConfig should return correct values from the given config file"() {
        def mailConfig = new EmailConfig("test_mail.properties")
        def properties = new Properties()
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("test_mail.properties"))


        when:
        def host = mailConfig.getSmtpHost()
        def password = mailConfig.getSmtpPassword()
        def port = mailConfig.getSmtpPort()
        def user = mailConfig.getSmtpUser()
        def topic = mailConfig.getTopic()
        def sender = mailConfig.getSender()
        def recipients = mailConfig.getRecipients()
        def authenticated = mailConfig.isAuthenticated()

        then:
        host == properties.getProperty("mail.smtp.host")
        port == properties.getProperty("mail.smtp.port").toInteger()
        user == properties.getProperty("mail.user")
        password == properties.getProperty("mail.password")
        topic == properties.getProperty("mail.topic")
        sender == properties.getProperty("mail.sender")
        recipients == properties.getProperty("mail.recipients")
        authenticated == false

    }
}
