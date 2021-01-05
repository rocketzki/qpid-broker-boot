package com.rocketzki.broker

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class QpidBrokerBootApp

fun main(args: Array<String>) {
    runApplication<QpidBrokerBootApp>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
