package com.sinem.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMqConfig {
@Value("${rabbitmq.exchange-auth")
    private String exchance;
    @Value("${rabbitmq.bindingKey")
    private String bindingKeyActivateCode;
    @Value("${rabbitmq.queueActivated")
    private String queueActivateCode;

    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchance);
    }

    @Bean
    Queue activatedCodeQueue(){
        return new Queue(queueActivateCode);
    }

    @Bean
    public Binding bindingActivatedCode(final Queue activatedCodeQueue,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(activatedCodeQueue).to(exchangeAuth()).with(bindingKeyActivateCode);

    }




}
