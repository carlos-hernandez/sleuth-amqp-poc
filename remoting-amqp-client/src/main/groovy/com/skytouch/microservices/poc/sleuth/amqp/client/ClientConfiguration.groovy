package com.skytouch.microservices.poc.sleuth.amqp.client

import com.skytouch.microservices.poc.sleuth.amqp.service.BookingService
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfiguration {

    @Bean
    Queue queue() {
        return new Queue('remoting.queue')
    }

    @Bean
    DirectExchange directExchange(Queue queue) {
        return new DirectExchange('remoting.exchange')
    }

    @Bean
    Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with('remoting.binding')
    }

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory factory) {
        def template = new RabbitTemplate(factory)
        template.routingKey = 'remoting.binding'
        template.exchange = 'remoting.exchange'
        template.replyTimeout = 2000

        return template
    }

    @Bean
    AmqpProxyFactoryBean amqpFactoryBean(AmqpTemplate amqpTemplate) {
        def factoryBean = new AmqpProxyFactoryBean()
        factoryBean.serviceInterface = BookingService
        factoryBean.amqpTemplate = amqpTemplate

        return factoryBean
    }
}
