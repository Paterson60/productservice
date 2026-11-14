package com.paterson.productservice.client.fakestore;

import com.paterson.productservice.client.fakestore.dtos.FakeStoreProductDto;
import com.paterson.productservice.dtos.GenericProductDto;
import com.paterson.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${fakestore.api.url}")
    private String fakeStoreApiBaseUrl;

    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;

    private String productUrl = fakeStoreApiBaseUrl + fakeStoreProductPath + "/{id}";
    private String productRequestUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.url}") String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.api.products}") String fakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreApiBaseUrl + fakeStoreProductPath + "/{id}";
        this.productRequestUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;
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
                restTemplate.postForEntity(productRequestUrl, genericProductDto, FakeStoreProductDto.class);
        FakeStoreProductDto createdProduct = response.getBody();
        return createdProduct;
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestUrl, FakeStoreProductDto[].class);
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
