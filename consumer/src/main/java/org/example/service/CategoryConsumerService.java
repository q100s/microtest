package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryConsumerService {
    @Value("${categories.supplier.service.url}")
    private static String supplierServiceUrl;
    private final RestTemplate restTemplate;

    public List<CategoryDto> getAllCategories() {
        ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(supplierServiceUrl, CategoryDto[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
