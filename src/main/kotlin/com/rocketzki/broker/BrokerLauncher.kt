package com.rocketzki.broker

import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class BrokerLauncher(private val brokerRunner: QpidConfig) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    @PostConstruct
    private fun runBroker() {
        executorService.execute(brokerRunner)
    }

    @PreDestroy
    fun shutdown() {
        executorService.shutdownNow()
    }
}