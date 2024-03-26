package com.vistext.letstextapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_records")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false)
        private Long userId;
        private String userNickName;
        private String userName;
        private long userPhoneNumber;
}
