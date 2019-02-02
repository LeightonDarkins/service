package com.leightondarkins.service

import org.jetbrains.exposed.sql.Database

class DatabaseConnector {
    companion object {
        fun connect() {
            val environment: String? = System.getenv("TRACKER_DEPLOYMENT_ENV")
            val hostname = getHostName(environment)
            val port = getPort(environment)

            Database.connect("jdbc:postgresql://$hostname:$port/trackerdb",
                    driver = "org.postgresql.Driver",
                    user = "trackeruser",
                    password = "trackeruserpassword")
        }

        private fun getHostName(environment: String?): String {
            return if (environment == "docker") "database" else "localhost"
        }

        private fun getPort(environment: String?): String {
            return if (environment == "docker") "5432" else "5433"
        }
    }
}