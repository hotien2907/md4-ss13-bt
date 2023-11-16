package com.ra.model.service;

import com.ra.model.dao.CategoryDAO;
import com.ra.model.dao.CategoryDAOImpl;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIpml implements CategoryService {


    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Boolean updateById(Category category, Integer id) {

        return categoryDAO.updateById(category, id);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDAO.deleteById(id);
    }
}
