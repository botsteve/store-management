package com.stefan.store.management.repositories.user;

import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.domain.repositories.user.UserRepository;
import com.stefan.store.management.entities.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUser(String username) {
        var user = Optional.ofNullable(userJpaRepository.findByUsername(username))
                .orElseThrow(NoSuchElementException::new);
        return UserMapper.mapToDomain(user);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserMapper.mapToEntity(user.toBuilder().password(encodedPassword).build());

        var newUser = userJpaRepository.save(userEntity);
        return UserMapper.mapToDomain(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(UserMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}