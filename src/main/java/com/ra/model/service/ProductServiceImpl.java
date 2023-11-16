package com.ra.model.service;

import com.ra.model.dao.ProductDAO;
import com.ra.model.entity.Product;
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
    public Boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public Boolean updateById(Product product, Integer id) {
        return productDAO.updateById(product, id);
    }

    @Override
    public void deleteById(Integer id) {

        productDAO.deleteById(id);
    }
}
