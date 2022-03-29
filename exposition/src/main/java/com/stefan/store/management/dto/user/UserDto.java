package com.stefan.store.management.dto.user;

import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.domain.entities.user.User;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto {

    String username;
    String password;
    String email;

    @Singular
    List<Product> purchases;

    public static UserDto fromDomain(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .purchases(user.getPurchases())
                .build();
    }

    public static User toDomain(UserDto user){
        return User.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .purchases(user.getPurchases())
                .build();
    }
}
