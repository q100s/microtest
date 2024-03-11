package com.q100s.microtest.service.impl;

import com.q100s.microtest.dto.CategoryDto;
import com.q100s.microtest.dto.CreateOrUpdateCategoryDto;
import com.q100s.microtest.dto.mapper.CategoryMapper;
import com.q100s.microtest.exception.DataNotFountException;
import com.q100s.microtest.model.Category;
import com.q100s.microtest.repository.CategoryRepository;
import com.q100s.microtest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.q100s.microtest.dto.mapper.CategoryMapper.mapIntoDto;
import static com.q100s.microtest.dto.mapper.CategoryMapper.mapIntoEntity;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto createCategory(CreateOrUpdateCategoryDto newCategory) {
        return mapIntoDto(repository.save(mapIntoEntity(newCategory)));
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.findAll().stream()
                .map(CategoryMapper::mapIntoDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long categoryId) {
        return mapIntoDto(repository.findById(categoryId).orElseThrow(DataNotFountException::new));
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CreateOrUpdateCategoryDto newProperties) {
        Category updatedCategory = repository.findById(categoryId).orElseThrow(DataNotFountException::new);
        Optional.ofNullable(newProperties.getName()).ifPresent(updatedCategory::setName);
        return mapIntoDto(repository.save(updatedCategory));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        repository.delete(repository.findById(categoryId).orElseThrow(DataNotFountException::new));
    }
}
