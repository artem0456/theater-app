package ru.epam.spring.hometask.dao;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketDAO extends AbstractDao<Ticket> {
	Set<Ticket> getByEventByDateTime(Event event, LocalDateTime dateTime);
	Set<Ticket> getAllForUser(User user);
}
