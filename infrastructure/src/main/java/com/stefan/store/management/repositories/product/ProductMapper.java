package com.stefan.store.management.repositories.product;

import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.entities.product.ProductEntity;
import com.stefan.store.management.entities.user.UserEntity;

public class ProductMapper {

    public static Product mapToDomain(ProductEntity productEntity) {
        return Product.builder()
                .idProduct(productEntity.getIdProduct())
                .name(productEntity.getName())
                .stock(productEntity.getStock())
                .price(productEntity.getPrice())
                .expirationDate(productEntity.getExpirationDate())
                .username(productEntity.getUser().getUsername())
                .build();
    }

    public static ProductEntity mapToEntity(Product product, UserEntity user) {
        return ProductEntity.builder()
                .idProduct(product.getIdProduct())
                .name(product.getName())
                .stock(product.getStock())
                .price(product.getPrice())
                .expirationDate(product.getExpirationDate())
                .user(user)
                .build();
    }
}
