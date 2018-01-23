package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.TicketDAO;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDAOImpl implements TicketDAO {
	
	private Map<Long, Ticket> tickets = new HashMap<>();
	
	private Long sequenceId = 0L;

	@Override
	public Ticket save(Ticket ticket) {
		if(ticket.getId() != null ) {
			tickets.put(ticket.getId(), ticket);
		} else {
			ticket.setId(sequenceId++);
			tickets.put(ticket.getId(), ticket);
		}
		return ticket;
	}

	@Override
	public void remove(Ticket ticket) {
		tickets.remove(ticket.getId());
	}

	@Override
	public Ticket getById(long id) {
		return tickets.get(id);
	}

	@Override
	public Set<Ticket> getByEventByDateTime(Event event, LocalDateTime dateTime) {
		return tickets.entrySet().stream()
				.filter( p -> p.getValue().getEvent().equals(event) && p.getValue().getDateTime().equals(dateTime))
				.map(e -> e.getValue())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Ticket> getAll() {
		return new HashSet<Ticket>(tickets.values());
	}
	
	@Override
	public Set<Ticket> getAllForUser(User user) {
		return tickets.entrySet().stream()
				.filter( p -> p.getValue().getUser() != null && p.getValue().getUser().equals(user))
				.map(e -> e.getValue())
				.collect(Collectors.toSet());
	}

}
