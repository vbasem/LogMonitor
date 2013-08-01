package monitor.config

import spock.lang.Specification

class EmailConfigSuite extends Specification {

    def "EmailConfig should return correct values from the given config file"() {
        def mailConfig = new EmailConfig("testmail.properties")
        def properties = new Properties()
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("testmail.properties"))


        when:
        def host = mailConfig.getSmtpHost()
        def password = mailConfig.getSmtpPassword()
        def port = mailConfig.getSmtpPort()
        def user = mailConfig.getSmtpUser()
        def topic = mailConfig.getTopic()
        def sender = mailConfig.getSender()

        then:
        host == properties.getProperty("smtp.host")
        port == properties.getProperty("smtp.port").toInteger()
        user == properties.getProperty("smtp.user")
        password == properties.getProperty("smtp.password")
        topic == properties.getProperty("mail.topic")
        sender == properties.getProperty("mail.sender")



    }
}
