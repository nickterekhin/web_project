package com.database.repository.impl;

import com.database.repository.IPersonRepository;
import com.database.repository.impl.list.ListPersonRepository;

/**
 * Created by Nick on 12.06.17.
 */
public class DAORepository {
    private IPersonRepository iPersonRepository;

    public DAORepository() {
        this.iPersonRepository = new ListPersonRepository();
    }

    public IPersonRepository getPersonRepository() {
        return iPersonRepository;
    }
}
