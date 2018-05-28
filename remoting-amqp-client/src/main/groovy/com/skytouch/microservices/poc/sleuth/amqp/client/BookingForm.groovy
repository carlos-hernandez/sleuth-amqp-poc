package com.skytouch.microservices.poc.sleuth.amqp.client

import javax.validation.constraints.NotEmpty

class BookingForm {

    @NotEmpty
    String location
}
