package com.q100s.microtest.service;

import com.q100s.microtest.model.Category;
import com.q100s.microtest.model.Product;
import com.q100s.microtest.repository.CategoryRepository;
import com.q100s.microtest.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static Product createProduct(String productName, String productDescription, String priceValue) {
        Product newProduct = new Product();
        newProduct.setName(productName);
        newProduct.setDescription(productDescription);
        newProduct.setPrice(new BigDecimal(priceValue));
        return newProduct;
    }

    private static Category createCategory(String categoryName) {
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        return newCategory;
    }

    @PostConstruct
    public void loadDataBase() {
        Category electronics = createCategory("Electronics");
        Product laptop = createProduct("Laptop", "Powerful laptop", "87500");
        Product smartphone = createProduct("Smartphone", "Powerful laptop", "50000");
        Product vacuumCleaner = createProduct("Vacuum cleaner", "Robotic", "15000");
        connectProductsWithCategoriesAndSaveIntoDB(electronics, laptop, smartphone, vacuumCleaner);

        Category clothing = createCategory("Clothing");
        Product tShirt = createProduct("T-shirt", "Cotton t-shirt", "2500");
        Product jeans = createProduct("Jeans", "Casual jeans", "3000");
        Product crocs = createProduct("Crocs", "Comfortable crocs", "500");
        connectProductsWithCategoriesAndSaveIntoDB(clothing, tShirt, jeans, crocs);

        Category grocery = createCategory("Grocery");
        Product bread = createProduct("Bread", "Wheat bread", "50");
        Product milk = createProduct("Milk", "1L Bottle of milk", "75");
        Product eggs = createProduct("Eggs", "Dozen eggs", "120");
        connectProductsWithCategoriesAndSaveIntoDB(grocery, bread, milk, eggs);
    }

    private void connectProductsWithCategoriesAndSaveIntoDB(Category category, Product... products) {
        List<Product> newProducts = new ArrayList<>();
        categoryRepository.save(category);
        for (Product product : products) {
            product.setCategory(category);
            newProducts.add(product);
            productRepository.save(product);
        }
        category.setProducts(newProducts);
    }
}