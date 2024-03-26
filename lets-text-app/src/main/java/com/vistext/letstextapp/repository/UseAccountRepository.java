package com.vistext.letstextapp.repository;

import com.vistext.letstextapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UseAccountRepository extends CrudRepository<User, Long> {
}
