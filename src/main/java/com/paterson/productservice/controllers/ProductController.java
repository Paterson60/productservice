package com.paterson.productservice.controllers;

import com.paterson.productservice.dtos.ExceptionDto;
import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.exceptions.NotFoundException;
import com.paterson.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) {
        return productService.updateProductById(id, genericProductDto);
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }


    //This exception is specific for ProductController
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex) {
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
//    }
}
