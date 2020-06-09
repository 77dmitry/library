package com.burst.library.dao;

import java.util.List;

public interface GeneralDao<T> {

    T save(T t);

    void update(T t);

    List<T> getAll();

    T getByName(String name);

    T getById(Long id);

    int delete(Long id);
}
