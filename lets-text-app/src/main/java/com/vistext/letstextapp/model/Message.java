package com.vistext.letstextapp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageContent;
}