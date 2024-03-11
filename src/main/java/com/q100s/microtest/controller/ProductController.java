package com.q100s.microtest.controller;

import com.q100s.microtest.dto.CreateOrUpdateProductDto;
import com.q100s.microtest.dto.ProductDto;
import com.q100s.microtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductDto> createProduct(@PathVariable("categoryId") Long categoryId,
                                                    @RequestBody CreateOrUpdateProductDto newProduct) {
        return ResponseEntity.ok(service.createProduct(newProduct, categoryId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(service.getById(productId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
                                                    @RequestBody CreateOrUpdateProductDto newProperties) {
        return ResponseEntity.ok(service.updateProduct(productId, newProperties));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        service.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}