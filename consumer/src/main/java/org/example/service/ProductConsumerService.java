package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateOrUpdateProductDto;
import org.example.dto.ProductDto;
import org.example.model.FilterParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductConsumerService {
    @Value("${products.supplier.service.url}")
    private static String supplierServiceUrl;
    private final RestTemplate restTemplate;

    private static List<ProductDto> filterProducts(FilterParameters params, List<ProductDto> allProducts) {
        return allProducts.stream().filter(product -> {
            boolean isCompiled = true;
            if (params.getProductMinPrice() != null && product.getPrice().compareTo(params.getProductMinPrice()) < 0) {
                isCompiled = false;
            }
            if (params.getProductMaxPrice() != null && product.getPrice().compareTo(params.getProductMaxPrice()) > 0) {
                isCompiled = false;
            }
            if (params.getCategoryId() != null && !Objects.equals(product.getCategoryId(), params.getCategoryId())) {
                isCompiled = false;
            }
            return isCompiled;
        }).toList();
    }

    private static UriComponentsBuilder buildUri(int page, int size) {
        return UriComponentsBuilder.fromHttpUrl(supplierServiceUrl)
                .queryParam("page", page)
                .queryParam("size", size);
    }

    public List<ProductDto> getAllProducts(int page, int size) {
        UriComponentsBuilder builder = buildUri(page, size);
        try {
            ResponseEntity<ProductDto[]> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    ProductDto[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to get products", e);
        }
    }

    public ProductDto addProduct(CreateOrUpdateProductDto product, Long categoryId) {
        try {
            ResponseEntity<ProductDto> response = restTemplate.postForEntity(
                    supplierServiceUrl + "/{categoryId}",
                    product,
                    ProductDto.class,
                    categoryId);
            return response.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to add product", e);
        }
    }

    public void updateProduct(Long productId, CreateOrUpdateProductDto newProperties) {
        try {
            restTemplate.put(supplierServiceUrl + "/{id}", newProperties, productId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to update product", e);
        }
    }

    public void deleteProduct(Long productId) {
        try {
            restTemplate.delete(supplierServiceUrl + "/{id}", productId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to delete product", e);
        }
    }

    public List<ProductDto> filterProducts(int page, int size, FilterParameters parameters) {
        List<ProductDto> allProducts = getAllProducts(page, size);
        try {
            return filterProducts(parameters, allProducts);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to search products", e);
        }
    }

    public List<ProductDto> searchProducts(int page, int size, String query) {
        try {
            return getAllProducts(page, size).stream()
                    .filter(dto -> dto.getName().contains(query) || dto.getDescription().contains(query))
                    .toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to search products", e);
        }
    }
}