package com.q100s.microtest.dto.mapper;

import com.q100s.microtest.dto.CreateOrUpdateProductDto;
import com.q100s.microtest.dto.ProductDto;
import com.q100s.microtest.model.Product;

public class ProductMapper {
    public static Product mapIntoEntity(CreateOrUpdateProductDto dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static ProductDto mapIntoDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }
}