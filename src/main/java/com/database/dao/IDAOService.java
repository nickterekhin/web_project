package com.database.dao;

import java.util.List;

/**
 * Created by Nick on 12.06.17.
 */
public interface IDAOService<T> {
    T create(T obj);
    void delete(T obj);
    void delete(long id);
    T getById(long id);
    T update(T obj);
    List<T> getAll();

}
