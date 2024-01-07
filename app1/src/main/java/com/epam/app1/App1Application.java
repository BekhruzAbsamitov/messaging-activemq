package com.epam.app1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class App1Application {

    @Autowired
    private Sender sender;

    private Integer step = 0;

    @Scheduled(fixedRate = 3000)
    public void sendToTopic() {
        Integer integer = step++;
        sender.send(String.valueOf(integer));
        log.info("Message {} ", integer);
    }

    public static void main(String[] args) {
        SpringApplication.run(App1Application.class, args);
    }

}
