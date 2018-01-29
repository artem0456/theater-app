package ru.epam.spring.hometask.dao;


import ru.epam.spring.hometask.domain.Event;

public interface EventDAO extends AbstractDao<Event> {
	Event getByName(String name);
}
