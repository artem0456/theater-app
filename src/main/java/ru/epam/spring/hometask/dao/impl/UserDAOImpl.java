package ru.epam.spring.hometask.dao.impl;

import ru.epam.spring.hometask.dao.UserDAO;
import ru.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserDAOImpl implements UserDAO {

	private Map<Long, User> users = new HashMap<>();
	
	private Long sequenceId = 0L;
	
	@Override
	public User save(User user) {
		
		if(user.getId() != null ) {
			users.put(user.getId(), user);
		} else {
			user.setId(sequenceId++);
			users.put(user.getId(), user);
		}
		return user;
	}

	@Override
	public void remove(User user) {
		users.remove(user.getId());
	}

	@Override
	public User getById(long id) {
		return users.get(id);
	}

	@Override
	public User getByEmail(String email) {
		for (User u : users.values()) {
	        if (u.getEmail().equals(email)) {
	            return u;
	        }
	    }
		return null;
	}

	@Override
	public Set<User> getAll() {
		return new HashSet<User>(users.values());
	}

}
