package com.vistext.letstextapp.repository;

import com.vistext.letstextapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessagesRepository extends JpaRepository<Message, String> {

    List<Message> findBySenderId(String senderId);

    List<Message> findByReceiverId(String receiverId);

    @Query("SELECT m FROM Message m WHERE m.receiverId = :senderUserId AND m.senderId = :receiverUserId")
    List<Message> findByReceivedMessagesFromUser(String senderUserId, String receiverUserId);
}
