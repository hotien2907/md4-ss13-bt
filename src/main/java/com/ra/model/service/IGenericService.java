package com.ra.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll();

    Boolean create(T t);
    T findById(ID id);
    Boolean updateById(T t, ID id);
    void deleteById(ID id);
}
