package com.example.dao;

import java.util.List;

public interface IDao<T> {
    List<T> get();

    T get(int idade);

    List<T> get(String termoBusca);

    int insert(T objeto);

    int update(T objeto);

    int delete(T objeto);
}
