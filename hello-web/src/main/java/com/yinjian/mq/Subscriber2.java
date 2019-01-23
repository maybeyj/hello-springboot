package com.yinjian.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author: Yin Jian
 * @create: 2019-01-18 15:52
 */
/*@Component*/
public class Subscriber2 {
    @JmsListener(destination = "topic")
    public void consumer(String message) {
        System.out.println("subscriber21112222:" + message);
    }
}
