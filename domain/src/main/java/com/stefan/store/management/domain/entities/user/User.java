package com.stefan.store.management.domain.entities.user;

import com.stefan.store.management.domain.entities.product.Product;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class User {

    long idUser;
    String username;
    String password;
    String email;

    @Builder.Default
    List<Product> purchases = new ArrayList<>();
}