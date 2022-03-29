package com.stefan.store.management.repositories.product;

import com.stefan.store.management.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByUserUsername(String username);
}
