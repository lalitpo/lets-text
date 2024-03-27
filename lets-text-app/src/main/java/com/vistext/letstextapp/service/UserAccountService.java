package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.User;
import com.vistext.letstextapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    /**
     * Creates a user account.
     *
     * @param user the user object containing the user's information
     * @throws Exception if the user with the same nickname already exists
     */
    public void createUserAccount(User user) throws Exception {
        if (userAccountRepository.findById(user.getUserNickName()).isPresent()) {
            throw new Exception("User with " + user.getUserNickName() + " already exists");
        }
        userAccountRepository.save(user);
    }
}
