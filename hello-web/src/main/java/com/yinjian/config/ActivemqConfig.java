package com.yinjian.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author: Yin Jian
 * @create: 2019-01-18 15:25
 */
@Configuration
public class ActivemqConfig {
    @Bean
    public Topic testTopic(){
        return new ActiveMQTopic("topic");
    }

    /*@Bean
    public Queue testQueue(){
        return new ActiveMQQueue("queue");
    }*/

}
