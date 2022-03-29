package com.stefan.store.management.repositories.user;

import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.entities.user.UserEntity;

public class UserMapper {

    public static User mapToDomain(UserEntity userEntity) {
        return User.builder()
                .idUser(userEntity.getIdUser())
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    public static UserEntity mapToEntity(User user) {
        return new UserEntity()
                .withIdUser(user.getIdUser())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withUsername(user.getUsername());
    }
}
