package com.database.repository;

import com.domain.Person;

/**
 * Created by Nick on 12.06.17.
 */
public interface IPersonRepository extends IRepository<Person,Long> {
    Person getByName(String personName);
    Person getByNameExcludeId(String personName, long id);
}
