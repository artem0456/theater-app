package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventServiceImpl implements EventService {

    private EventServiceDao events;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return events.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return events.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        events.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return events.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events.getAll();
    }

    public EventServiceDao getEvents() {
        return events;
    }

    public void setEvents(EventServiceDao events) {
        this.events = events;
    }
}
