package com.paterson.productservice.repositories;

import com.paterson.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleAndPrice_Currency(String title, String currency);

//    @Query(value = SqlQueries.GET_ALL_PRODUCTS_BY_TITLE, nativeQuery = true)
//    Product findByTitle(String title);
//
//    @Query(value = "select Product from Product where Product.title= :title")
//    Product findByTitle2(String title);
}
