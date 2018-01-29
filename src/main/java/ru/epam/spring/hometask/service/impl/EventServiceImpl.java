package ru.epam.spring.hometask.service.impl;

import java.util.Collection;

import ru.epam.spring.hometask.service.EventService;
import ru.epam.spring.hometask.dao.EventDAO;
import ru.epam.spring.hometask.domain.Event;

public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    @Override
    public Event save(Event object) {
        return eventDAO.save(object);
    }

    @Override
    public void remove(Event object) {
        eventDAO.remove(object);
    }

    @Override
    public Event getById(Long id) {
        return eventDAO.getById(id);
    }

    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }

    @Override
    public Event getByName(String name) {
        return eventDAO.getByName(name);
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

}
