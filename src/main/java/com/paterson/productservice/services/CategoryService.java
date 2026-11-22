package com.paterson.productservice.services;

import com.paterson.productservice.models.Category;
import com.paterson.productservice.models.Product;
import com.paterson.productservice.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void getCategory(String uuid){
       Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
       Category category = categoryOptional.get();
       System.out.println(category);

        List<Product> products = category.getProducts();
        System.out.println("Products in category: " + products);
    }

    public void getProductList(List<String> uuid){

        List<UUID> uuids = uuid.stream().map(UUID::fromString).toList();

        List<Category> categories = categoryRepository.findAllById(uuids);
        for(Category category : categories){
            for(Product product : category.getProducts()){
                System.out.println(product.getTitle());
            }
        }
    }
}
