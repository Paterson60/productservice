package com.paterson.productservice.controllers;

import com.paterson.productservice.dtos.ProductListRequestDto;
import com.paterson.productservice.models.Product;
import com.paterson.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{uuid}")
    public void getCategory(@PathVariable("uuid") String uuid) {
        categoryService.getCategory(uuid);

    }

    @GetMapping("products")
    public void getProductsByCategory(@RequestBody ProductListRequestDto productListRequestDto) {
        categoryService.getProductList(productListRequestDto.getUuid());
    }
}
