package com.vistext.letstextapp.controllers;

import com.vistext.letstextapp.model.Message;
import com.vistext.letstextapp.service.UserMessagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class UserMessagingController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserMessagingService messageService;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        try {
            messageService.sendMessage(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Message sending failed : " + e.getMessage());
        }
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/allsent/{userId}")
    public ResponseEntity<Object> getSentMessages(@PathVariable String userId) {
        try {
            List<Message> sentMessages = messageService.getAllSentMessages(userId);
            return ResponseEntity.ok(sentMessages);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Message sending failed : " + e.getMessage());
        }
    }

    @GetMapping("/allreceived/{userId}")
    public ResponseEntity<Object> getReceivedMessages(@PathVariable String userId) {
        try {
            List<Message> receivedMessages = messageService.getAllReceivedMessages(userId);
            return ResponseEntity.ok(receivedMessages);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Message sending failed : " + e.getMessage());
        }
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<Object> getReceivedMessages(@PathVariable String userId, @RequestParam String receiverUserId) {
        try {
            List<Message> receivedMessages = messageService.getReceivedMessagesFromUser(userId, receiverUserId);
            return ResponseEntity.ok(receivedMessages);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Message sending failed : " + e.getMessage());
        }
    }
}
