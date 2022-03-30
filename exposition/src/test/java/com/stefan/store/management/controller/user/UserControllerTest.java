package com.stefan.store.management.controller.user;

import com.stefan.store.management.BaseTestClass;
import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.services.user.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
public class UserControllerTest extends BaseTestClass {

    private static final String USERNAME_USER1 = "test";
    private static final String USERNAME_USER2 = "test2";
    private static final String EMAIL_USER1 = "test@test.com";
    private static final String EMAIL_USER2 = "test2@test.com";
    private static final String PASSWORD_USER1 = "test";
    private static final String PASSWORD_USER2 = "test2";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserFacade userFacade;

    @Test
    @WithMockUser(username = "test",password = "test")
    public void shouldReturnAllUsers() throws Exception {
        when(userFacade.getAllUsers()).thenReturn(users());
        mockMvc.perform(get("/users")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].username", is(USERNAME_USER1)))
                .andExpect(jsonPath("[1].username", is(USERNAME_USER2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test",password = "test")
    public void shouldReturnASingleUser() throws Exception {
        var user = getUser(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1);
        when(userFacade.getUser(USERNAME_USER1))
                .thenReturn(user);
        mockMvc.perform(get(format("/users/%s", USERNAME_USER1))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(USERNAME_USER1))
                .andExpect(jsonPath("password", is(PASSWORD_USER1)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test",password = "test")
    public void shouldCreateASingleUser() throws Exception {
        var newUser = getUser(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1);
        when(userFacade.createUser(newUser))
                .thenReturn(newUser);
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(jackson.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(USERNAME_USER1))
                .andExpect(jsonPath("password", is(PASSWORD_USER1)))
                .andDo(print());
    }

    private List<User> users() {
        return List.of(
                getUser(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1),
                getUser(USERNAME_USER2, PASSWORD_USER2, EMAIL_USER2)
        );
    }

    private User getUser(String username, String password, String email) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
