package com.sinem.rabbitmq.procuder;

import com.sinem.rabbitmq.model.ActivateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivatedCodeProcedure {
    @Value("${rabbitmq.exchange-auth")
    private String exchance;
    @Value("${rabbitmq.bindingKey")
    private String bindingKeyActivateCode;
    private final RabbitTemplate rabbitTemplate;

    public void sendActivatedCode(ActivateRequestDto dto){
        rabbitTemplate.convertAndSend(exchance,bindingKeyActivateCode,dto);
    }


}
