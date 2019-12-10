package com.leaves.smalltiger.common.utils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName,String message){
        amqpTemplate.convertAndSend(queueName,message);
    }

}
