package com.stefan.store.management.repositories.user;

import com.stefan.store.management.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
