package com.poly.j6d8_asm_ph24125.service;

import com.poly.j6d8_asm_ph24125.entity.Product;

import java.util.List;


public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);

}
