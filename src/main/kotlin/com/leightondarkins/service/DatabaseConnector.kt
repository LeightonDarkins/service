package com.leightondarkins.service

import org.apache.logging.log4j.LogManager
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

class DatabaseConnector(private val hostname: String, private val port: String) {
    private val logger = LogManager.getLogger("DatabaseConnector")
    private val user = "trackeruser"
    private val password = "trackeruserpassword"

    fun migrate() {
        logger.info("Running Migration Against DB @ host: $hostname. via port: $port")

        val flyway = Flyway()
        flyway.setDataSource(getUrl(), user, password)

        flyway.migrate()
    }

    fun connect() {
        logger.info("Connecting to PostgreSQL @ host: $hostname. via port: $port")

        Database.connect(getUrl(),
                driver = "org.postgresql.Driver",
                user = user,
                password = password)
    }

    private fun getUrl(): String {
        return "jdbc:postgresql://$hostname:$port/trackerdb"
    }
}