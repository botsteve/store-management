package com.stefan.store.management.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefan.store.management.entities.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;

    @Column(nullable = false)
    private String name;

    private double price;

    private int stock;

    @Builder.Default
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

}

