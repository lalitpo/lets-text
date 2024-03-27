package com.vistext.letstextapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_messages")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "message_id", nullable = false)
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageContent;
    private Date messageSentTime;

    public Message(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}