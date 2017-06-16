package com.database.dao;

import com.domain.Person;

/**
 * Created by Nick on 12.06.17.
 */
public interface IDAOPerson extends IDAOService<Person>{
    Person getByName(String personName);
    Person getByName(String personName,long excludeId);
}
