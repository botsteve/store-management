package com.stefan.store.management.domain.repositories.product;

import com.stefan.store.management.domain.entities.product.Product;

import java.util.List;

public interface ProductRepository {
    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product modifyProduct(Product product);

    List<Product> findAllProductsByUsername(String username);

    List<Product> getAllProducts();
}
