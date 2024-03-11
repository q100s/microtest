package com.q100s.microtest.service.impl;

import com.q100s.microtest.dto.CreateOrUpdateProductDto;
import com.q100s.microtest.dto.ProductDto;
import com.q100s.microtest.dto.mapper.ProductMapper;
import com.q100s.microtest.exception.DataNotFountException;
import com.q100s.microtest.model.Product;
import com.q100s.microtest.repository.CategoryRepository;
import com.q100s.microtest.repository.ProductRepository;
import com.q100s.microtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.q100s.microtest.dto.mapper.ProductMapper.mapIntoDto;
import static com.q100s.microtest.dto.mapper.ProductMapper.mapIntoEntity;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(CreateOrUpdateProductDto newProperties, Long categoryId) {
        Product newProduct = mapIntoEntity(newProperties);
        newProduct.setCategory(categoryRepository.findById(categoryId)
                .orElseThrow(DataNotFountException::new));
        return mapIntoDto(repository.save(newProduct));
    }

    @Override
    public List<ProductDto> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).stream()
                .map(ProductMapper::mapIntoDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long productId) {
        return mapIntoDto(repository.findById(productId).orElseThrow(DataNotFountException::new));
    }

    @Override
    public ProductDto updateProduct(Long productId, CreateOrUpdateProductDto newProperties) {
        Product updatedProduct = repository.findById(productId).orElseThrow(DataNotFountException::new);
        Optional.ofNullable(newProperties.getName()).ifPresent(updatedProduct::setName);
        Optional.ofNullable(newProperties.getDescription()).ifPresent(updatedProduct::setDescription);
        Optional.ofNullable(newProperties.getPrice()).ifPresent(updatedProduct::setPrice);
        return mapIntoDto(repository.save(updatedProduct));
    }

    @Override
    public void deleteProduct(Long productId) {
        repository.delete(repository.findById(productId).orElseThrow(DataNotFountException::new));
    }
}
