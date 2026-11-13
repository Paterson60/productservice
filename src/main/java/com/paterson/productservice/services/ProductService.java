package com.paterson.productservice.services;

import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);

    GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto);
}
