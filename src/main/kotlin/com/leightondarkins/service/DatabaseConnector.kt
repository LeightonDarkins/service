package com.leightondarkins.service

import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.sql.Database

class DatabaseConnector(private val hostname: String, private val port: String) {
    private val logger = LogManager.getLogger("DatabaseConnector")

    fun connect() {
        logger.info("Connecting to PostgreSQL @ host: $hostname. via port: $port")

        Database.connect("jdbc:postgresql://$hostname:$port/trackerdb",
                driver = "org.postgresql.Driver",
                user = "trackeruser",
                password = "trackeruserpassword")
    }
}