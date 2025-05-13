package com.example.lab4.dao;

import java.util.Collection;

public interface AbstractDao<T> {
    void delete(T entity);

    T get(Integer id);

    Collection<T> getAll();

    void create(T entity);

    void update(T entity);

}
