package com.ra.model.dao;

import java.util.List;

public interface IGenericDAO<T,ID>{
    List<T> findAll();

    Boolean create(T t);
    T findById(ID id);
    Boolean updateById(T t, ID id);
    void deleteById(ID id);
}
