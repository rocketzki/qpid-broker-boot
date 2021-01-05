package com.rocketzki.broker

import lombok.SneakyThrows
import org.apache.qpid.server.SystemLauncher
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class BrokerRunner : QpidConfig {

    @Value("\${qpid-broker-boot.http-port}")
    private val httpPort: String? = null

    @Value("\${qpid-broker-boot.amqp-port}")
    private val amqpPort: String? = null

    private val systemLauncher = SystemLauncher()

    override fun createSystemConfig(): Map<String, Any?> {
        val initialConfig = BrokerRunner::class.java.classLoader.getResource("config.json")
        return mapOf(
            "type" to "Memory",
            "initialConfigurationLocation" to Objects.requireNonNull(initialConfig).toExternalForm(),
            "startupLoggedToSystemOut" to true,
            "qpid.http_port" to httpPort,
            "qpid.amqp_port" to amqpPort
        )
    }

    @SneakyThrows
    override fun run() {
        systemLauncher.startup(createSystemConfig())
    }

    override fun destroy() {
        systemLauncher.shutdown()
    }
}