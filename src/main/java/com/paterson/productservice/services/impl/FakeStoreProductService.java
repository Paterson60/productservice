package com.paterson.productservice.services.impl;

import com.paterson.productservice.client.fakestore.FakeStoreProductClient;
import com.paterson.productservice.client.fakestore.dtos.FakeStoreProductDto;
import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.exceptions.NotFoundException;
import com.paterson.productservice.services.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeProductNameService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductClient fakeStoreProductClient;

    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        return genericProductDto;
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.createProduct(genericProductDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.updateProductById(id, genericProductDto));
    }
}
