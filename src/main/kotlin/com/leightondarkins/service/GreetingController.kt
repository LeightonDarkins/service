package com.leightondarkins.service

import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController(private val properties: ServiceProperties) {
    private val logger = LogManager.getLogger("GreetingController")

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World!") name: String): Greeting {
        logger.info("ENV: ${properties.environment}")
        logger.info("Sending a greeting")

        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }
}