package com.skytouch.microservices.poc.sleuth.amqp.server.service

import com.skytouch.microservices.poc.sleuth.amqp.model.Booking
import com.skytouch.microservices.poc.sleuth.amqp.service.BookingServiceContract
import groovy.util.logging.Slf4j
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Slf4j
@Service
class BookingService implements BookingServiceContract {

    @Override
    @RabbitListener(queues = 'remoting.queue')
    Booking bookRide(String pickUpLocation) {
        log.info 'Book a ride for this pickup location: {}', pickUpLocation
        return new Booking(bookingCode: UUID.randomUUID().toString())
    }
}
