package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.model.Booking
import com.skytouch.microservices.poc.sleuth.amqp.service.BookingServiceContract
import groovy.util.logging.Slf4j
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.cloud.sleuth.annotation.NewSpan
import org.springframework.stereotype.Service

@Slf4j
@Service
class BookingServiceClient implements BookingServiceContract {

    private RabbitTemplate rabbitTemplate

    BookingServiceClient(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate
    }

    @Override
    @NewSpan
    Booking bookRide(String pickUpLocation) {
        log.info 'Send a request to book a new ride for this pickup location: {}', pickUpLocation

        def booking = rabbitTemplate.convertSendAndReceive('remoting.exchange', 'remoting.binding', pickUpLocation) as Booking
        log.info 'Ride confirmed with code {}', booking.bookingCode

        return booking
    }
}
