package com.epam.app1;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {

    private final JmsTemplate jmsTemplate;
    private final ActiveMQQueue queue;

    @Autowired
    public Sender(JmsTemplate jmsTemplate, ActiveMQQueue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @SneakyThrows
    public String send(String message) {
        log.info("Sending message: {} to queue: {}", message, queue.getQueueName());
        TextMessage msg = (TextMessage) jmsTemplate.sendAndReceive(queue, s -> s.createTextMessage(message));
        return msg.getText();
    }
}
