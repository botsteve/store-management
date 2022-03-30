package com.stefan.store.management.services.product;

import com.stefan.store.management.domain.repositories.product.ProductRepository;
import com.stefan.store.management.domain.services.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.stefan.store.management.services.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void shouldReturnAllProducts() {
        when(productRepository.getAllProducts()).thenReturn(products());
        var users = productService.getAllProducts();
        assertThat(users).isEqualTo(products());
    }

    @Test
    public void shouldReturnAProduct() {
        var expected = product(USERNAME_USER1, PROD1_NAME, PROD1_PRICE, PROD1_STOCK);
        when(productRepository.getProduct(any())).thenReturn(expected);
        var actual = productService.getProduct(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCreateProduct() {
        var expected = product(USERNAME_USER2, PROD3_NAME, PROD3_PRICE, PROD3_STOCK);
        when(productRepository.createProduct(expected)).thenReturn(expected);
        var actual = productService.createProduct(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldModifyExistingProduct() {
        var expected = product(USERNAME_USER2, PROD3_NAME, PROD3_PRICE, PROD3_STOCK);
        when(productRepository.modifyProduct(expected)).thenReturn(expected);
        var actual = productService.modifyProduct(expected);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void shouldReturnAllProductsByUsername() {
        when(productRepository.findAllProductsByUsername(USERNAME_USER1)).thenReturn(products());
        var products = productService.getAllProductsByUsername(USERNAME_USER1);
        assertThat(products).hasSize(2);
    }

}