package com.stefan.store.management.entities.product;

import com.stefan.store.management.entities.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {

    private long idProduct;
    private String name;
    private double price;
    private int stock;
    private LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
    private User user;
}