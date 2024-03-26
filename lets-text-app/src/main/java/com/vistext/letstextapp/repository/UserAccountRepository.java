package com.vistext.letstextapp.repository;

import com.vistext.letstextapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<User, String> {
}
