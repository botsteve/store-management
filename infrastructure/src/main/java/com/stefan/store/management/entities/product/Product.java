package com.stefan.store.management.entities.product;

import com.stefan.store.management.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long idProduct;

    @Column(nullable = false)
    private String name;

    private double price;

    private int stock;

    private LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUser")
    private User user;

}

