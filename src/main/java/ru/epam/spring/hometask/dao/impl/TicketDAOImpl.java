package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.TicketDAO;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDAOImpl extends AbstractDaoImpl<Ticket> implements TicketDAO {

	@Override
	public Set<Ticket> getByEventByDateTime(Event event, LocalDateTime dateTime) {
		return objects.entrySet().stream()
				.filter( p -> p.getValue().getEvent().equals(event) && p.getValue().getDateTime().equals(dateTime))
				.map(Map.Entry::getValue)
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<Ticket> getAllForUser(User user) {
		return objects.entrySet().stream()
				.filter( p -> p.getValue().getUser() != null && p.getValue().getUser().equals(user))
				.map(Map.Entry::getValue)
				.collect(Collectors.toSet());
	}

}
