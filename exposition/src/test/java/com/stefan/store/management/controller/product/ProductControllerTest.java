package com.stefan.store.management.controller.product;

import com.stefan.store.management.BaseTestClass;
import com.stefan.store.management.domain.entities.product.Product;
import com.stefan.store.management.domain.services.product.ProductService;
import com.stefan.store.management.dto.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductController.class)
public class ProductControllerTest extends BaseTestClass {

    public static final String USERNAME = "test";
    public static final int STOCK_PRODUCT1 = 12;
    public static final double PRICE_PRODUCT1 = 11.0;
    public static final String NAME_PRODUCT1 = "test_product";
    public static final long PRODUCT_ID = 1L;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(products());

        mockMvc.perform(get("/products")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].username", is(USERNAME)))
                .andExpect(jsonPath("[0].name", is(NAME_PRODUCT1)))
                .andExpect(jsonPath("[0].price", is(PRICE_PRODUCT1)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnAProduct() throws Exception {
        when(productService.getProduct(PRODUCT_ID)).thenReturn(product());

        mockMvc.perform(get(format("/products/%d", PRODUCT_ID))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is(USERNAME)))
                .andExpect(jsonPath("name", is(NAME_PRODUCT1)))
                .andExpect(jsonPath("price", is(PRICE_PRODUCT1)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldCreateProduct() throws Exception {
        when(productService.createProduct(Mockito.any())).thenReturn(product());

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(jackson.writeValueAsString(productDto())))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldModifyProduct() throws Exception {
        when(productService.modifyProduct(Mockito.any())).thenReturn(product());

        mockMvc.perform(put("/products")
                        .contentType(APPLICATION_JSON)
                        .content(jackson.writeValueAsString(product())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is(USERNAME)))
                .andExpect(jsonPath("name", is(NAME_PRODUCT1)))
                .andExpect(jsonPath("price", is(PRICE_PRODUCT1)))
                .andDo(print());
    }

    private List<Product> products() {
        return List.of(product());
    }

    private Product product() {
        return Product.builder()
                .idProduct(PRODUCT_ID)
                .name(NAME_PRODUCT1)
                .price(PRICE_PRODUCT1)
                .stock(STOCK_PRODUCT1)
                .username(USERNAME)
                .build();
    }

    private ProductDto productDto() {
        return ProductDto.builder()
                .idProduct(PRODUCT_ID)
                .name(NAME_PRODUCT1)
                .price(PRICE_PRODUCT1)
                .stock(STOCK_PRODUCT1)
                .username(USERNAME)
                .build();
    }
}
