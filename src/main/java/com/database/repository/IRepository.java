package com.database.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nick on 12.06.17.
 */
public interface IRepository<T,ID extends Serializable> {
    T create(T obj);
    void delete(T obj);
    void delete(ID id);
    T getById(ID id);
    T update(T obj);
    List<T> getAll();
}
