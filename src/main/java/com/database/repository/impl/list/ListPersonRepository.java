package com.database.repository.impl.list;

import com.database.repository.IPersonRepository;
import com.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nick on 12.06.17.
 */
public class ListPersonRepository implements IPersonRepository{
    private List<Person> personList = new ArrayList<>(Arrays.asList(new Person(1, "Nick", 13), new Person(2, "Vasia", 14)));


    @Override
    public Person create(Person obj) {
        obj.setId(personList.size()+1);
        personList.add(obj);
        return obj;
    }

    @Override
    public void delete(Person obj) {
        this.delete(obj.getId());
    }

    @Override
    public void delete(Long id) {
        for (Person p : personList) {
            if (p.getId() == id) {
                personList.remove(p);
                break;
            }
        }
    }

    @Override
    public Person getById(Long id) {

        for (Person p : personList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Person update(Person obj) {
        personList.set(personList.indexOf(obj), obj);
        return obj;
    }

    @Override
    public List<Person> getAll() {
        return personList;
    }

    @Override
    public Person getByName(String personName) {
        for(Person p : personList)
        {
            if(p.getName().equals(personName))
            {
                return  p;
            }
        }
        return null;
    }

    @Override
    public Person getByNameExcludeId(String personName, long id) {
        for(Person p : personList)
        {
            if(p.getName().equals(personName) && p.getId()!=id)
            {
                return  p;
            }
        }
        return null;
    }
}
