package com.skytouch.microservices.poc.sleuth.amqp.service

import com.skytouch.microservices.poc.sleuth.amqp.model.Booking

interface BookingServiceContract {

    Booking bookRide(String pickUpLocation)
}
