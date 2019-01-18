package com.yinjian.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author: Yin Jian
 * @create: 2019-01-18 15:21
 */
@RestController
@RequestMapping("mq")
public class MqController {
    @Autowired
    @Qualifier("testTopic")
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping("topic")
    public String send(String message) {
        jmsMessagingTemplate.convertAndSend(topic, message);
        return message;
    }

}
