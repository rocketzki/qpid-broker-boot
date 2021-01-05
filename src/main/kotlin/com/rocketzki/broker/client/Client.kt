package com.rocketzki.broker.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import lombok.extern.slf4j.Slf4j
import org.apache.qpid.jms.JmsConnectionFactory
import org.apache.qpid.jms.JmsQueue
import org.springframework.boot.json.JacksonJsonParser
import javax.jms.Connection
import javax.jms.Message
import javax.jms.Session

@Slf4j
object Client {

    private const val DEFAULT_HOST = "amqp://localhost:5672"
    private const val DEFAULT_USER = "theUser"
    private const val DEFAULT_SECRET = "theP@55"
    private const val DEFAULT_CLIENT_ID = "my_client_test"

    private val parser = JacksonJsonParser()

    private val objMapper: ObjectMapper = ObjectMapper()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .findAndRegisterModules()


    private var connection: Connection? = null

    fun createConnFactory(): JmsConnectionFactory {
        return createConnFactory(DEFAULT_HOST, DEFAULT_USER, DEFAULT_SECRET, DEFAULT_CLIENT_ID)
    }

    fun createConnFactory(host: String, user: String, secret: String, clientId: String): JmsConnectionFactory {
        val jms = JmsConnectionFactory(user, secret, host)
        jms.clientID = clientId
        jms.isReceiveLocalOnly = true
        jms.closeTimeout = 500L
        return jms
    }

    fun send(destination: String, msg: String, session: Session?) {
        val parsedToMap = parser.parseMap(msg)
        val convertedToString = objMapper.writeValueAsString(parsedToMap)
        val message = session?.createTextMessage(convertedToString)

        val producer = session?.createProducer(JmsQueue(destination))
        producer?.deliveryMode = 1

        println("### Sending message '$msg' to '$destination'")
        producer?.send(message)

        session?.close()
    }

    fun send(destination: String, msg: String, createConn: Boolean) {
        if (connection == null || createConn) {
            println("### Creating connection  ")
            connection = createConnFactory().createConnection()
        }

        send(destination, msg, connection?.createSession())
    }

    fun send(destination: String, msg: String) {
        send(destination, msg, false)
    }

}
