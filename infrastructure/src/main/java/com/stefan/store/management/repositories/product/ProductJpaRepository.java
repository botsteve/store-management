package com.stefan.store.management.repositories.product;

import com.stefan.store.management.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByUserUsername(String username);
}
