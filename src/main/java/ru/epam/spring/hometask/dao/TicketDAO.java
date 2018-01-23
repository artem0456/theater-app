package ru.epam.spring.hometask.dao;

import java.time.LocalDateTime;
import java.util.Set;

import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;

public interface TicketDAO {
	
	Ticket save(Ticket ticket);
	
	void remove(Ticket ticket);
	
	Ticket getById(long id);
	
	Set<Ticket> getByEventByDateTime(Event event, LocalDateTime dateTime);
	
	Set<Ticket> getAll();
	
	Set<Ticket> getAllForUser(User user);
}
