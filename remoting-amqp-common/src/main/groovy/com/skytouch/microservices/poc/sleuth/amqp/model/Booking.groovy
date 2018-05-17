package com.skytouch.microservices.poc.sleuth.amqp.model

class Booking implements Serializable {

    private String bookingCode

    Booking(String bookingCode) {
        this.bookingCode = bookingCode
    }

    @Override
    String toString() {
        return String.format("Ride confirmed: code '%s'.", bookingCode)
    }
}
