package ru.epam.spring.hometask.dao;

import java.util.Set;

public interface AbstractDao<T> {

    T save(T object);

    void remove(T object);

    T getById(long id);

    Set<T> getAll();

}
