package com.skytouch.microservices.poc.sleuth.amqp.server.service

import com.skytouch.microservices.poc.sleuth.amqp.model.Booking
import com.skytouch.microservices.poc.sleuth.amqp.service.BookingService
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class BookingServiceImpl implements BookingService {

    @Override
    Booking bookRide(String pickUpLocation) {
        log.info 'Book ride for the following pickup location: {}', pickUpLocation
        return new Booking(UUID.randomUUID().toString())
    }
}
