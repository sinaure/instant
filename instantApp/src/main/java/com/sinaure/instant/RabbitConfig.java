package com.sinaure.instant;


import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.bedatadriven.jackson.datatype.jts.JtsModule;


@Configuration
public class RabbitConfig implements RabbitListenerConfigurer {
    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    @PostConstruct
    public void init() {
    	rabbitAdmin().declareQueue(
                new org.springframework.amqp.core.Queue("main", false));
    	rabbitAdmin().declareExchange(
                new org.springframework.amqp.core.TopicExchange("main_exchange", false, true));
        rabbitAdmin()
                .declareBinding(new org.springframework.amqp.core.Binding("main",
                        DestinationType.QUEUE, "main_exchange", "event", null));
       
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setPort(rabbitmqProperties.getPort());
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        return connectionFactory;
    }
    

    @Bean(name = "rabbitAdmin")
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }


    @Bean(name = "containerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setMissingQueuesFatal(false);
        return factory;
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
            Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
            builder.modulesToInstall(new JtsModule());
            return builder;
    }

    // listener convert bytes to Message
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static AbstractMessageListenerContainer startListening(RabbitAdmin rabbitAdmin, String queue,
            String exchange, String key, MessageListener messageListener) {
        rabbitAdmin.declareBinding(
                new org.springframework.amqp.core.Binding(queue, DestinationType.QUEUE, exchange, "*", null));
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(
                rabbitAdmin.getRabbitTemplate().getConnectionFactory());
        listener.addQueueNames(queue);
        listener.setMessageListener(messageListener);
        return listener;
    }

}