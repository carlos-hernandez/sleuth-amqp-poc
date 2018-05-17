package com.skytouch.microservices.poc.sleuth.amqp.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AmqpServer {

    static void main(String[] args) {
        SpringApplication.run(AmqpServer, args)
    }
}
