package com.paterson.productservice.services.impl;

import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductNameService")
public class SelfProductServiceImpl implements ProductService {

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }


    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        return null;
    }

}
