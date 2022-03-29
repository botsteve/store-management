package com.stefan.store.management.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @With
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @With
    @Column(nullable = false, unique = true)
    private String username;

    @With
    @Column(nullable = false)
    private String password;

    @With
    @Column(nullable = false, unique = true)
    private String email;
}