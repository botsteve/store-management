package com.stefan.store.management.repositories.user;

import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.domain.repositories.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User getUser(String username) {
        var user = Optional.ofNullable(userJpaRepository.findByUsername(username))
                .orElseThrow(NoSuchElementException::new);
        return UserMapper.mapToDomain(user);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        var newUser = userJpaRepository.save(UserMapper.mapToEntity(user));
        return UserMapper.mapToDomain(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(UserMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}