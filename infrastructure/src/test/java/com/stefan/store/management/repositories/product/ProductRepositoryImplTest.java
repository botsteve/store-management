package com.stefan.store.management.repositories.product;

import com.stefan.store.management.repositories.DbTestUtil;
import com.stefan.store.management.repositories.user.UserJpaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static com.stefan.store.management.repositories.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductRepositoryImplTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @BeforeAll
    public void init() {
        userJpaRepository.saveAll(users());
    }

    @BeforeEach
    public void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "products");
        var user = userJpaRepository.findByUsername(USERNAME_USER1);
        productJpaRepository.saveAll(products(user));
    }

    @AfterEach
    public void cleanUp() {
        productJpaRepository.deleteAll();
    }

    @Test
    public void shouldThrowExceptionIfNotFound() {
        assertThatThrownBy(() -> productRepository.getProduct(Long.MAX_VALUE)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void shouldReturnProduct() {
        var product = productRepository.getProduct(1L);
        assertThat(product.getUsername()).isEqualTo(USERNAME_USER1);
        assertThat(product.getName()).isEqualTo(PROD1_NAME);
        assertThat(product.getPrice()).isEqualTo(PROD1_PRICE);
        assertThat(product.getStock()).isEqualTo(PROD1_STOCK);
    }

    @Test
    public void shouldReturnAllProducts() {
        var products = productRepository.getAllProducts();
        assertThat(products).hasSize(2);
    }

    @Test
    public void shouldReturnProductsByUsername() {
        var products = productRepository.findAllProductsByUsername(USERNAME_USER1);
        assertThat(products).hasSize(2);
    }

    @Test
    public void shouldCreateProduct() {
        var user = userJpaRepository.findByUsername(USERNAME_USER2);
        var product = product(user, PROD3_NAME, PROD3_PRICE, PROD3_STOCK);
        var newProduct = productRepository.createProduct(ProductMapper.mapToDomain(product));
        assertThat(newProduct.getUsername()).isEqualTo(USERNAME_USER2);
        assertThat(newProduct.getName()).isEqualTo(PROD3_NAME);
        assertThat(newProduct.getPrice()).isEqualTo(PROD3_PRICE);
        assertThat(newProduct.getStock()).isEqualTo(PROD3_STOCK);
    }

    @Test
    public void shouldModifyExistingProduct() {
        var existingProduct = productRepository.getProduct(1L);
        var modifiedProduct = existingProduct.toBuilder().stock(PROD3_STOCK).build();
        var newlyModifiedProduct = productRepository.modifyProduct(modifiedProduct);
        assertThat(newlyModifiedProduct.getUsername()).isEqualTo(USERNAME_USER1);
        assertThat(newlyModifiedProduct.getStock()).isEqualTo(PROD3_STOCK);
    }

}