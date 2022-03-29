package com.stefan.store.management.domain.repositories.user;

import com.stefan.store.management.domain.entities.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User getUser(String username);

    User createUser(User user);

    List<User> getAllUsers();
}
