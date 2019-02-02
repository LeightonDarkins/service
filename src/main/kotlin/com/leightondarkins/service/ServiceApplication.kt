package com.leightondarkins.service

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ServiceApplication

fun main(args: Array<String>) {
    Database.connect("jdbc:postgresql://localhost:5433/trackerdb",
            driver = "org.postgresql.Driver",
            user = "trackeruser",
            password = "trackeruserpassword")

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(Transactions)
    }

    SpringApplication.run(ServiceApplication::class.java, *args)
}

