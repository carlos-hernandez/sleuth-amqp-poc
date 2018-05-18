package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingServiceContract
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
@Slf4j
class AmqpClient implements CommandLineRunner {

    private ApplicationContext applicationContext
    private BookingServiceContract bookingServiceClient

    AmqpClient(ApplicationContext applicationContext, BookingServiceContract bookingServiceClient) {
        this.applicationContext = applicationContext
        this.bookingServiceClient = bookingServiceClient
    }

    static void main(String[] args) {
        SpringApplication.run(AmqpClient, args)
    }

    @Override
    void run(String... args) throws Exception {
        bookingServiceClient.bookRide('13 Seagate Blvd, Key Largo, FL 33037')

        System.exit(SpringApplication.exit(applicationContext))
    }
}
