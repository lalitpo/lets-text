package com.vistext.letstextapp.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger logger = LogManager.getLogger();

    @KafkaListener(topics = "text-topic", groupId = "text-groups")
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }

}
