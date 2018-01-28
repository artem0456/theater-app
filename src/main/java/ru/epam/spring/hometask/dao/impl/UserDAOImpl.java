package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.UserDAO;
import ru.epam.spring.hometask.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDAOImpl extends AbstractDaoImpl<User> implements UserDAO {

	@Override
	public User getByEmail(String email) {
//		for (User u : objects.values()) {
//	        if (u.getEmail().equals(email)) {
//	            return u;
//	        }
//	    }
//		return null;

		List<User> listEvent = objects.values()
				.stream()
				.filter(value -> value.getEmail().equals(email))
				.collect(Collectors.toList());
		if (!listEvent.isEmpty()) return listEvent.get(0);
		return null;
	}

}
