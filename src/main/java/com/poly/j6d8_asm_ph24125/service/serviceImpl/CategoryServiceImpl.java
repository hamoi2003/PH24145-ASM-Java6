package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.dao.CategoryDAO;
import com.poly.j6d8_asm_ph24125.entity.Category;
import com.poly.j6d8_asm_ph24125.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
}
