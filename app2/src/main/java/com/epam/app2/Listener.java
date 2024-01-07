package com.epam.app2;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class Listener {

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "my-queue", containerFactory = "defaultJmsListenerContainerFactory")
    public void receive(Message message) throws JMSException {
        TextMessage msg = (TextMessage) message;
        log.info("Message received: {}", msg.getText());
        jmsTemplate.convertAndSend(msg.getJMSReplyTo(), "Time: " + LocalDate.now());
    }
}