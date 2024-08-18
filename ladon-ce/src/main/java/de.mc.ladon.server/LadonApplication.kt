/*
 * Copyright (c) 2024 Mind Consulting GmbH
 */

package de.mc.ladon.server

import de.mc.ladon.server.boot.config.LadonS3ConfigImpl
import de.mc.ladon.server.boot.config.ServerConfigImpl
import org.apache.catalina.connector.Connector
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling


/**
 * LadonApplication
 * Created by Ralf Ulrich
 */
@SpringBootApplication(
    scanBasePackages = ["de.mc.ladon"]
)
@EnableScheduling
@EnableConfigurationProperties(
    ServerConfigImpl::class,
    LadonS3ConfigImpl::class
)
open class LadonApplication {

    @Value("\${http.port:8080}")
    private val httpPort = 0

    @Bean
    open fun servletContainer(): ServletWebServerFactory? {
        val tomcat = TomcatServletWebServerFactory()
        tomcat.addAdditionalTomcatConnectors(createStandardConnector())
        return tomcat
    }

    private fun createStandardConnector(): Connector? {
        val connector = Connector("org.apache.coyote.http11.Http11NioProtocol")
        connector.setPort(httpPort)
        return connector
    }

}

fun main(args: Array<String>) {
    System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
    SpringApplication.run(LadonApplication::class.java, *args)

}
