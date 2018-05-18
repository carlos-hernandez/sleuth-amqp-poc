package com.skytouch.microservices.poc.sleuth.amqp.client

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfiguration {

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        def rabbitTemplate = new RabbitTemplate(connectionFactory)
        rabbitTemplate.with {
            replyTimeout = 2000
            messageConverter = new Jackson2JsonMessageConverter()
        }

        return rabbitTemplate
    }
}
