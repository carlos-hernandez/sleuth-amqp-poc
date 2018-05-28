package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingServiceContract
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@Slf4j
@SpringBootApplication
class AmqpClient {

    private ApplicationContext applicationContext
    private BookingServiceContract bookingServiceClient

    AmqpClient(ApplicationContext applicationContext, BookingServiceContract bookingServiceClient) {
        this.applicationContext = applicationContext
        this.bookingServiceClient = bookingServiceClient
    }

    static void main(String[] args) {
        SpringApplication.run(AmqpClient, args)
    }
}
