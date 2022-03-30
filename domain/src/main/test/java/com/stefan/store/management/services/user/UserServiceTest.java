package com.stefan.store.management.services.user;

import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.domain.repositories.user.UserRepository;
import com.stefan.store.management.domain.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.stefan.store.management.services.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void shouldReturnAllUsers() {
        when(userRepository.getAllUsers()).thenReturn(users());
        var users = userService.getAllUsers();
        assertThat(users).isEqualTo(users());
    }

    @Test
    public void shouldReturnAUser() {
        var expected = user(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1);
        when(userRepository.getUser(USERNAME_USER1)).thenReturn(expected);
        var user = userService.getUser(USERNAME_USER1);
        assertThat(user).isEqualTo(expected);
    }

    @Test
    public void shouldCreateNewUser() {
        var expected = user(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1);
        when(userRepository.createUser(expected)).thenReturn(expected);
        User actual = userService.createUser(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
