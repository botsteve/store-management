package com.stefan.store.management.services.user;

import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.domain.entities.user.User;
import com.stefan.store.management.domain.services.product.ProductService;
import com.stefan.store.management.domain.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final ProductService productService;

    public User getUser(String username){
        List<Product> allProductsByUsername = productService.getAllProductsByUsername(username);
        return userService.getUser(username).toBuilder()
                .purchases(allProductsByUsername)
                .build();
    }

    public User createUser(User user){
        return userService.createUser(user);
    }

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
