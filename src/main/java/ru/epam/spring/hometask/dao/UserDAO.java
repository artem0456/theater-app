package ru.epam.spring.hometask.dao;

import java.util.Set;

import ru.epam.spring.hometask.domain.User;

public interface UserDAO {
	
	User save(User user);
	
	void remove(User user);
	
	User getById(long id);
	
	User getByEmail(String email);
	
	Set<User> getAll();
}
