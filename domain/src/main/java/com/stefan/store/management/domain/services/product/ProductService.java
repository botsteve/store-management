package com.stefan.store.management.domain.services.product;

import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.domain.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product modifyProduct(Product product) {
        return productRepository.modifyProduct(product);
    }

    public List<Product> getAllProductsByUsername(String username) {
        return productRepository.findAllProductsByUsername(username);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
