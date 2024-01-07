package com.epam.app3;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {

    @JmsListener(destination = "Consumer.my-consumer.VirtualTopic.my-topic", containerFactory = "queueListenerFactory")
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received: {}", textMessage.getText());
    }
}