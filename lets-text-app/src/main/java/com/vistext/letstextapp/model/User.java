package com.vistext.letstextapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_records")
@Entity
public class User {
    @Id
    @Column(name = "user_nickname", nullable = false)
    private String userNickName;
    private String userName;
    private long userPhoneNumber;
}
