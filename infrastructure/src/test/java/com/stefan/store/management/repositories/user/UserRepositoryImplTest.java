package com.stefan.store.management.repositories.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static com.stefan.store.management.repositories.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    public void setUp() {
        userJpaRepository.saveAll(users());
    }

    @AfterEach
    public void cleanUp() {
        userJpaRepository.deleteAll();
    }

    @Test
    public void shouldReturnAllUsers() {
        var users = userRepository.getAllUsers();
        assertThat(users).hasSize(2);
    }


    @Test
    public void shouldReturnAUserWithTheSpecifiedUsername() {
        var user = userRepository.getUser(USERNAME_USER1);

        assertThat(user.getUsername()).isEqualTo(USERNAME_USER1);
        assertThat(user.getEmail()).isEqualTo(EMAIL_USER1);
        assertThat(user.getPassword()).isEqualTo(PASSWORD_USER1);
    }

    @Test
    public void shouldThrowWhenNoUserFound() {
        assertThatThrownBy(() -> userRepository.getUser(UNKNOWN_USERNAME)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void shouldCreateNewUser() {
        var user = userRepository
                .createUser(UserMapper.mapToDomain(user(NEW_USER_USERNAME, NEW_USER_PASSWORD, NEW_USER_EMAIL)));
        assertThat(user.getUsername()).isEqualTo(NEW_USER_USERNAME);
        assertThat(user.getPassword()).isEqualTo(NEW_USER_PASSWORD);
        assertThat(user.getEmail()).isEqualTo(NEW_USER_EMAIL);
    }
}