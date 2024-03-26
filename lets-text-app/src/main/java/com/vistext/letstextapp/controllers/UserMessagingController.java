package com.vistext.letstextapp.controllers;

import com.vistext.letstextapp.model.Message;
import com.vistext.letstextapp.service.UserMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class UserMessagingController {

    @Autowired
    private UserMessagingService messageService;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        try {
            messageService.sendMessage(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Message sending failed : " + e.getMessage());
        }
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/allsent/{userId}")
    public ResponseEntity<List<Message>> getSentMessages(@PathVariable String userId) {
        List<Message> sentMessages = messageService.getAllSentMessages(userId);
        return ResponseEntity.ok(sentMessages);
    }

    @GetMapping("/allreceived/{userId}")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable String userId) {
        List<Message> receivedMessages = messageService.getAllReceivedMessages(userId);
        return ResponseEntity.ok(receivedMessages);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable String userId, @RequestParam String receiverUserId) {
        List<Message> receivedMessages = messageService.getReceivedMessagesFromUser(userId, receiverUserId);
        return ResponseEntity.ok(receivedMessages);
    }
}
