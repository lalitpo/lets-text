package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.Message;
import com.vistext.letstextapp.repository.UserAccountRepository;
import com.vistext.letstextapp.repository.UserMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        return userMessagesRepository.findBySenderId(userId);
    }

    public List<Message> getAllReceivedMessages(String userId) {
        return userMessagesRepository.findByReceiverId(userId);
    }

    public List<Message> getReceivedMessagesFromUser(String senderUserId, String receiverUserId) {
        return userMessagesRepository.findByReceivedMessagesFromUser(senderUserId, receiverUserId);
    }

    public void sendMessage(Message message) throws Exception {

        if (userAccountRepository.findById(message.getSenderId()).isEmpty()) {
            throw new Exception("User " + message.getSenderId() + " does not exists");
        } else if (userAccountRepository.findById(message.getReceiverId()).isEmpty()) {
            throw new Exception("User " + message.getReceiverId() + " does not exists");
        } else if (message.getSenderId().equals(message.getReceiverId())) {
            throw new Exception("Users cannot send message to themselves.");
        }
        userMessagesRepository.save(message);
        if (isKafkaEnabled) {
            try{
                kafkaSender.send(kafkaTopic, message.getMessageContent());
            }
            catch (Exception e){
                throw new Exception("Failed to send message to Kafka");
            }
        }
    }
}