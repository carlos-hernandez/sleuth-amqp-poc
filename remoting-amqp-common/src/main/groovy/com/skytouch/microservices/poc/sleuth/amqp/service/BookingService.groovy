package com.skytouch.microservices.poc.sleuth.amqp.service

import com.skytouch.microservices.poc.sleuth.amqp.model.Booking

interface BookingService {

    Booking bookRide(String pickUpLocation)
}
