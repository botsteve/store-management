package com.stefan.store.management.domain.entities.product;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Product {

    long idProduct;
    String name;
    double price;
    int stock;
    @Builder.Default
    LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
    String username;
}