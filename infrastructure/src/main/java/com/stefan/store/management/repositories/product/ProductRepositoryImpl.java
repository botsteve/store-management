package com.stefan.store.management.repositories.product;


import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.domain.repositories.product.ProductRepository;
import com.stefan.store.management.entities.user.UserEntity;
import com.stefan.store.management.repositories.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {


    private final ProductJpaRepository productRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Product getProduct(Long productId) {
        var product = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        return ProductMapper.mapToDomain(product);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        var user = getUserByUsername(product.getUsername());
        var newProduct = productRepository.save(ProductMapper.mapToEntity(product,user));
        return ProductMapper.mapToDomain(newProduct);
    }

    @Override
    @Transactional
    public Product modifyProduct(Product product) {
        var user = getUserByUsername(product.getUsername());
        var modifiedProduct = productRepository.save(ProductMapper.mapToEntity(product,user));
        return ProductMapper.mapToDomain(modifiedProduct);
    }

    @Override
    public List<Product> findAllProductsByUsername(String username) {
        return productRepository.findByUserUsername(username).stream()
                .map(ProductMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToDomain)
                .collect(Collectors.toList());
    }


    private UserEntity getUserByUsername(String username){
        return Optional.ofNullable(userJpaRepository.findByUsername(username))
                .orElseThrow(NoSuchElementException::new);
    }
}