package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingServiceContract
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

import javax.validation.Valid

@Slf4j
@Controller
class IndexController {

    private BookingServiceContract bookingServiceClient

    IndexController(BookingServiceContract bookingServiceClient) {
        this.bookingServiceClient = bookingServiceClient
    }

    @GetMapping(value = '/')
    String home() {
        return 'index'
    }

    @PostMapping(value = '/')
    String loginPage(@Valid BookingForm bookingForm) {
        log.info 'Request to book a new ride received'

        bookingServiceClient.bookRide(bookingForm.location)

        log.info 'Request sent successfully'
        return "redirect:/"
    }
}
