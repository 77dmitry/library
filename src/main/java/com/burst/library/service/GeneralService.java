package com.burst.library.service;

import java.util.List;

public interface GeneralService<T>{

    List<T> getAll();

    Object getByName(String name);

    Object getById(Long id);

    T add(T t);

    void delete(Long id);

}
