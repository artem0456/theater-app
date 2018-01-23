package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.EventDAO;
import ru.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventDAOImpl implements EventDAO {
	
	private Map<Long, Event> events = new HashMap<>();
	
	private Long sequenceId = 0L;

	@Override
	public Event save(Event event) {
		if(event.getId() != null ) {
			events.put(event.getId(), event);
		} else {
			event.setId(sequenceId++);
			events.put(event.getId(), event);
		}
		return event;
	}

	@Override
	public void remove(Event event) {
		events.remove(event.getId());

	}

	@Override
	public Event getById(long id) {
		return events.get(id);
	}

	@Override
	public Event getByName(String name) {
		for (Event e : events.values()) {
	        if (e.getName().equals(name)) {
	            return e;
	        }
	    }
		return null;
	}

	@Override
	public Set<Event> getAll() {
		return new HashSet<Event>(events.values());
	}

}
