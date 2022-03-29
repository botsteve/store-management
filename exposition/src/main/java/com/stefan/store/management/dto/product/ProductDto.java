package com.stefan.store.management.dto.product;

import com.stefan.store.management.domain.entities.product.Product;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ProductDto {

    long idProduct;
    String name;
    double price;
    int stock;
    @Builder.Default
    LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
    String username;

    public static ProductDto fromDomain(Product product){
        return ProductDto.builder()
                .idProduct(product.getIdProduct())
                .name(product.getName())
                .price(product.getPrice())
                .expirationDate(product.getExpirationDate())
                .stock(product.getStock())
                .username(product.getUsername())
                .build();
    }

    public static Product toDomain(ProductDto productDto){
        return Product.builder()
                .idProduct(productDto.getIdProduct())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .expirationDate(productDto.getExpirationDate())
                .stock(productDto.getStock())
                .username(productDto.getUsername())
                .build();
    }
}