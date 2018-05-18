package com.skytouch.microservices.poc.sleuth.amqp.server

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory

@Configuration
@EnableRabbit
class ServerConfiguration implements RabbitListenerConfigurer {

    @Bean
    Queue queue() {
        return new Queue('remoting.queue')
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange('remoting.exchange')
    }

    @Bean
    Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with('remoting.binding')
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        def listenerContainerFactory = new SimpleRabbitListenerContainerFactory()
        listenerContainerFactory.with {
            connectionFactory = rabbitConnectionFactory
            messageConverter = new Jackson2JsonMessageConverter()
        }

        return listenerContainerFactory
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        def messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory()
        messageHandlerMethodFactory.with {
            messageConverter = new MappingJackson2MessageConverter()
        }

        return messageHandlerMethodFactory
    }

    @Override
    void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory())
    }
}
