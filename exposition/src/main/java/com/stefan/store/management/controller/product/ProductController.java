package com.stefan.store.management.controller.product;

import com.stefan.store.management.domain.services.product.ProductService;
import com.stefan.store.management.dto.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.stefan.store.management.dto.product.ProductDto.fromDomain;
import static com.stefan.store.management.dto.product.ProductDto.toDomain;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts().stream()
                .map(ProductDto::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(Long productId){
        return fromDomain(productService.getProduct(productId));
    }

    @PostMapping()
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return fromDomain(productService.createProduct(toDomain(productDto)));
    }

    @PutMapping()
    public ProductDto modifyProduct(@RequestBody ProductDto productDto){
        return fromDomain(productService.modifyProduct(toDomain(productDto)));
    }
}