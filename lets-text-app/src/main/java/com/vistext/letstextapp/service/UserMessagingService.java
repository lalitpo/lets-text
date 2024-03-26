package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMessagingService {
    private final List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessagesBySenderId(String senderId) {
        return messages.stream()
                .filter(message -> message.getSenderId().equals(senderId))
                .collect(Collectors.toList());
    }

    public List<Message> getAllSentMessages(String userId) {
        return new ArrayList<>();
    }

    public List<Message> getAllReceivedMessages(String userId) {
        return new ArrayList<>();
    }

    public List<Message> getReceivedMessagesFromUser(String userId, String receiverUserId) {
        return new ArrayList<>();
    }

    public void sendMessage(String userId, String message) {
    }
}