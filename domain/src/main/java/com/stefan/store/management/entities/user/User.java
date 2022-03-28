package com.stefan.store.management.entities.user;

import com.stefan.store.management.entities.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class User {

    private long idUser;
    private String username;
    private String password;
    private String email;

    @Singular
    private List<Product> purchases;
}