package com.stefan.store.management.controller.user;

import com.stefan.store.management.dto.user.UserDto;
import com.stefan.store.management.services.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.stefan.store.management.dto.user.UserDto.fromDomain;
import static com.stefan.store.management.dto.user.UserDto.toDomain;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable String username) {
        return fromDomain(userFacade.getUser(username));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return fromDomain(userFacade.createUser(toDomain(userDto)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers().stream()
                .map(UserDto::fromDomain)
                .collect(Collectors.toList());
    }

}