package com.vistext.letstextapp.controllers;

import com.vistext.letstextapp.model.User;
import com.vistext.letstextapp.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/create")
    public ResponseEntity<String> createUserAccount(@RequestBody User user) {
        try{
            userAccountService.createUserAccount(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User account creation failed");
        }
        return ResponseEntity.ok("User account created successfully");
    }
}
