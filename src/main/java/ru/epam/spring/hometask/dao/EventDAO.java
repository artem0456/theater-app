package ru.epam.spring.hometask.dao;

import java.util.Set;

import ru.epam.spring.hometask.domain.Event;

public interface EventDAO {
	
	Event save(Event event);
	
	void remove(Event event);
	
	Event getById(long id);
	
	Event getByName(String name);
	
	Set<Event> getAll();
}
