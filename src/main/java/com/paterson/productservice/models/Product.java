package com.paterson.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {


    private String title;
    private String description;
    private Double image;
    private Category category;
    private double price;
}
