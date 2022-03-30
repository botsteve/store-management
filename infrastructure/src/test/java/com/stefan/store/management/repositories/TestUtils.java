package com.stefan.store.management.repositories;

import com.stefan.store.management.entities.product.ProductEntity;
import com.stefan.store.management.entities.user.UserEntity;

import java.util.List;

public class TestUtils {

    public static final String USERNAME_USER1 = "test";
    public static final String USERNAME_USER2 = "test2";
    public static final String NEW_USER_USERNAME = "new_user";
    public static final String EMAIL_USER1 = "test@test.com";
    public static final String EMAIL_USER2 = "test2@test.com";
    public static final String NEW_USER_EMAIL = "new_user@test.com";
    public static final String UNKNOWN_USERNAME = "xxxx";
    public static final String PASSWORD_USER1 = "test";
    public static final String PASSWORD_USER2 = "test2";
    public static final String NEW_USER_PASSWORD = "password";
    public static final String PROD1_NAME = "test_prod1";
    public static final String PROD2_NAME = "test_prod2";
    public static final String PROD3_NAME = "test_prod3";
    public static final int PROD1_PRICE = 11;
    public static final int PROD2_PRICE = 50;
    public static final int PROD3_PRICE = 100;
    public static final int PROD1_STOCK = 12;
    public static final int PROD2_STOCK = 55;
    public static final int PROD3_STOCK = 2000;


    public static UserEntity user(String username, String password, String email) {
        return new UserEntity()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email);
    }

    public static List<UserEntity> users() {
        return List.of(
                user(USERNAME_USER1, PASSWORD_USER1, EMAIL_USER1),
                user(USERNAME_USER2, PASSWORD_USER2, EMAIL_USER2)
        );
    }

    public static List<ProductEntity> products(UserEntity user) {
        return List.of(
                product(user, PROD1_NAME, PROD1_PRICE, PROD1_STOCK),
                product(user, PROD2_NAME, PROD2_PRICE, PROD2_STOCK)
        );
    }

    public static ProductEntity product(UserEntity user, String name, int price, int stock) {
        return ProductEntity.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .user(user)
                .build();
    }

}
