package com.paterson.productservice.client.fakestore;

import com.paterson.productservice.client.fakestore.dtos.FakeStoreProductDto;
import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreProductClient {

    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(productUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with ID " + id + " not found in Fake Store API");
        }

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(createProductUrl, genericProductDto, FakeStoreProductDto.class);
        FakeStoreProductDto createdProduct = response.getBody();
        return createdProduct;
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(createProductUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        List<FakeStoreProductDto> fakeStoreProductDtosList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            fakeStoreProductDtosList.add(fakeStoreProductDto);
        }
        return fakeStoreProductDtosList;
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(
                        productUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productUrl,
                HttpMethod.PUT,
                new HttpEntity<>(genericProductDto),
                FakeStoreProductDto.class,
                id
        );
        FakeStoreProductDto updatedProduct = response.getBody();
        return updatedProduct;
    }
}
