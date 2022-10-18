package com.sinem.utility;


import java.util.List;

public interface IService<T, ID> {
    T save(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T update(T t);
    void delete(T t);
    void deleteById(ID id);
    T findById(ID id);
    List<T> findAll();
}
