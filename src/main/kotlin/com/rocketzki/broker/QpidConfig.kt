package com.rocketzki.broker

import org.springframework.beans.factory.DisposableBean

interface QpidConfig : Runnable, DisposableBean {
    fun createSystemConfig(): Map<String, Any?>
}
