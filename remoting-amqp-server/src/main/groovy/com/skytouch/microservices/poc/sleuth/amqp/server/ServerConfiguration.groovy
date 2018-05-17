package com.skytouch.microservices.poc.sleuth.amqp.server

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingService
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServerConfiguration {

    @Bean
    Queue queue() {
        return new Queue('remoting.queue')
    }

    @Bean
    AmqpInvokerServiceExporter exporter(BookingService implementation, AmqpTemplate template) {
        def exporter = new AmqpInvokerServiceExporter()
        exporter.serviceInterface = BookingService
        exporter.service = implementation
        exporter.amqpTemplate = template

        return exporter
    }

    @Bean
    SimpleMessageListenerContainer listener(ConnectionFactory factory, AmqpInvokerServiceExporter exporter, Queue queue) {
        def container = new SimpleMessageListenerContainer(factory)
        container.messageListener = exporter
        container.setQueueNames(queue.name)

        return container
    }
}
