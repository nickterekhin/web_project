package com.database;

import com.database.dao.IDAOPerson;
import com.database.dao.impl.DAOPerson;

/**
 * Created by Nick on 12.06.17.
 */
public class DBContext {
    private IDAOPerson persons;

    public DBContext() {
        this.persons = new DAOPerson();
    }

    public IDAOPerson getPersons() {
        return persons;
    }
}
