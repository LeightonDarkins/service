package com.leightondarkins.service

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "service")
class ServiceProperties {
    lateinit var environment: String
    lateinit var dbHost: String
    lateinit var dbPort: String
}