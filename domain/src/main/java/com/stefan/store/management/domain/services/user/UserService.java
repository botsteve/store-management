package com.stefan.store.management.domain.services.user;

import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String username){
        return userRepository.getUser(username);
    }

    public User createUser(User user){
        return userRepository.createUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}