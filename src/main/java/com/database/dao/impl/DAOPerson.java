package com.database.dao.impl;

import com.database.dao.DAOService;
import com.database.dao.IDAOPerson;
import com.domain.Person;

import java.util.List;

/**
 * Created by Nick on 12.06.17.
 */
public class DAOPerson extends DAOService implements IDAOPerson{


    @Override
    public Person create(Person obj) {
        return this.daoRepository.getPersonRepository().create(obj);
    }

    @Override
    public void delete(Person obj) {
        this.daoRepository.getPersonRepository().delete(obj);
    }

    @Override
    public void delete(long id) {
        this.daoRepository.getPersonRepository().delete(id);
    }

    @Override
    public Person getById(long id) {
        return this.daoRepository.getPersonRepository().getById(id);
    }

    @Override
    public Person update(Person obj) {
        return this.daoRepository.getPersonRepository().update(obj);
    }

    @Override
    public List<Person> getAll() {
        return this.daoRepository.getPersonRepository().getAll();
    }

    @Override
    public Person getByName(String personName) {
        return this.daoRepository.getPersonRepository().getByName(personName);
    }

    @Override
    public Person getByName(String personName, long excludeId) {
        return this.daoRepository.getPersonRepository().getByNameExcludeId(personName,excludeId);
    }

}
