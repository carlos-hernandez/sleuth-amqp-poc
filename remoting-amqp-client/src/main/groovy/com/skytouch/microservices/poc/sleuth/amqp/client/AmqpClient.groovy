package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingService
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
@Slf4j
class AmqpClient implements CommandLineRunner {

    private BookingService bookingService
    private ApplicationContext applicationContext

    AmqpClient(BookingService bookingService, ApplicationContext applicationContext) {
        this.bookingService = bookingService
        this.applicationContext = applicationContext
    }

    static void main(String[] args) {
        SpringApplication.run(AmqpClient, args)
    }

    @Override
    void run(String... args) throws Exception {
        def booking = bookingService.bookRide("13 Seagate Blvd, Key Largo, FL 33037")
        log.info '{}', booking

        System.exit(SpringApplication.exit(applicationContext))
    }
}
