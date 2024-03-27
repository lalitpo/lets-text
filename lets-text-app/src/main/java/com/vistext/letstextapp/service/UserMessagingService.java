package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.Message;
import com.vistext.letstextapp.repository.UserAccountRepository;
import com.vistext.letstextapp.repository.UserMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessagingService {

    @Value("${kafka.enabled}")
    private boolean isKafkaEnabled;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private UserMessagesRepository userMessagesRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaSender;

    public List<Message> getAllSentMessages(String userId) {
        if (userExists(userId)) {
            return userMessagesRepository.findBySenderId(userId);
        }
        throw new IllegalArgumentException("Sender with ID " + userId + " does not exist.");
    }

    public List<Message> getAllReceivedMessages(String userId) {
        if (userExists(userId)) {
            return userMessagesRepository.findByReceiverId(userId);
        }
        throw new IllegalArgumentException("Sender with ID " + userId + " does not exist.");
    }

    public List<Message> getReceivedMessagesFromUser(String senderUserId, String receiverUserId) {
        if ((userExists(senderUserId)) && userExists(receiverUserId)) {
            return userMessagesRepository.findByReceivedMessagesFromUser(senderUserId, receiverUserId);
        }
        throw new IllegalArgumentException("One or both of the users does not exist in the system.");
    }

    /**
     * Sends a message and handles exceptions based on the existence of sender and receiver, and whether the sender is the same as the receiver.
     *
     * @param message the message to be sent
     */
    public void sendMessage(Message message) {
        validateMessage(message);
        userMessagesRepository.save(message);

        if (isKafkaEnabled) {
            sendMessageToKafka(message);
        }
    }

    private void validateMessage(Message message) {
        String senderId = message.getSenderId();
        String receiverId = message.getReceiverId();

        if (senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Users cannot send messages to themselves.");
        }

        if (!userExists(senderId)) {
            throw new IllegalArgumentException("Sender with ID " + senderId + " does not exist.");
        }

        if (!userExists(receiverId)) {
            throw new IllegalArgumentException("Receiver with ID " + receiverId + " does not exist.");
        }
    }

    private boolean userExists(String userId) {
        return userAccountRepository.findById(userId).isPresent();
    }

    private void sendMessageToKafka(Message message) throws KafkaException {
        try {
            kafkaSender.send(kafkaTopic, message.getMessageContent());
        } catch (Exception e) {
            throw new KafkaException("Failed to send message to Kafka : " + e.getMessage());
        }
    }

}