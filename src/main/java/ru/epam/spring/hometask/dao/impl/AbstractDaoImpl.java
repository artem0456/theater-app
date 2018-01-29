package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.AbstractDao;
import ru.epam.spring.hometask.domain.DomainObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbstractDaoImpl<T extends DomainObject> implements AbstractDao<T> {

      Map<Long, T> objects = new HashMap<>();
      Long sequenceId = 0L;

    @Override
    public T save(T object) {
        if(object.getId() != null ) {
            objects.put(object.getId(), object);
        } else {
            object.setId(sequenceId++);
            objects.put(object.getId(), object);
        }
        return object;
    }

    @Override
    public void remove(T object) {
        objects.remove(object.getId());
    }

    @Override
    public T getById(long id) {
        return objects.get(id);
    }

    @Override
    public Set<T> getAll() {
        return new HashSet<T>(objects.values());
    }
}
