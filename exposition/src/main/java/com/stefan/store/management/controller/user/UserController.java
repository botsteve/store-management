package com.stefan.store.management.controller.user;

import com.stefan.store.management.dto.user.UserDto;
import com.stefan.store.management.services.user.UserFacade;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{username}")
    public UserDto getUser(String username){
        return fromDomain(userFacade.getUser(username));
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){
        return fromDomain(userFacade.createUser(toDomain(userDto)));
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userFacade.getAllUsers().stream()
                .map(UserDto::fromDomain)
                .collect(Collectors.toList());
    }

}