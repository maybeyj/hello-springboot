package com.yinjian.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @author: Yin Jian
 * @create: 2019-01-18 11:49
 */
/*@Component*/
public class Subscriber {

    @JmsListener(destination = "topic")
    public void receive(String message) {
        System.out.println("subscriber1:" + message);
    }
}
