package com.leightondarkins.service

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(ServiceProperties::class)
class ServiceApplication

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(ServiceApplication::class.java, *args)
    val config = appContext.getBean(ServiceProperties::class.java)

    DatabaseConnector(config.dbHost, config.dbPort).connect()

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(Transactions)
    }

    println("Running Service In Environment: ${config.environment}")
}

