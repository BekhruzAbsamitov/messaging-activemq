package com.epam.app1;

import jakarta.jms.Topic;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {
    private final JmsTemplate jmsTemplate;
    private final Topic topic;

    @Autowired
    public Sender(JmsTemplate jmsTemplate, Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic = topic;
    }

    @SneakyThrows
    public void send(String message) {
        log.info("Sending message {} to topic {}", message, topic.getTopicName());
        jmsTemplate.convertAndSend(topic, message);
    }
}
