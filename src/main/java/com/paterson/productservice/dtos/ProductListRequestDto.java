package com.paterson.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ProductListRequestDto {

    private List<String> uuid;
}
