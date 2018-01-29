package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.EventDAO;
import ru.epam.spring.hometask.domain.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventDAOImpl extends AbstractDaoImpl<Event> implements EventDAO {
	

	@Override
	public Event getByName(String name) {
		List<Event> listEvent = objects.values()
                .stream()
                .filter(value -> value.getName().equals(name))
                .collect(Collectors.toList());
		if (!listEvent.isEmpty()) return listEvent.get(0);
		return null;

	}
}
