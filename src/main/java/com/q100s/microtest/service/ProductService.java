package com.q100s.microtest.service;

import com.q100s.microtest.dto.CreateOrUpdateProductDto;
import com.q100s.microtest.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(CreateOrUpdateProductDto newProduct, Long categoryId);

    List<ProductDto> getAll(int page, int size);

    ProductDto getById(Long productId);

    ProductDto updateProduct(Long productId, CreateOrUpdateProductDto newProperties);

    void deleteProduct(Long productId);
}
