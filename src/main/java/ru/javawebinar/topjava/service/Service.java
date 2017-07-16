package ru.javawebinar.topjava.service;

import java.util.List;

/**
 * Created by Pyltsin on 16.07.2017.
 */
public interface Service<T> {
    List<T> getAll();
    void add(T t);
    void delete(T t);
    void update(T t);
}
