package com.epam.app3;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {

    @Autowired
    private Topic topic;

    @JmsListener(destination = "my-topic", containerFactory = "durableJmsListenerContainerFactory")
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received message: {} from topic: {}", textMessage.getText(), topic.getTopicName());
    }
}