package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.dao.ProductDAO;
import com.poly.j6d8_asm_ph24125.entity.Product;
import com.poly.j6d8_asm_ph24125.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id).get();
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return productDAO.findByCategoryId(cid);
    }

    @Override
    public Product create(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Product update(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void delete(Integer id) {
         productDAO.deleteById(id);
    }
}
