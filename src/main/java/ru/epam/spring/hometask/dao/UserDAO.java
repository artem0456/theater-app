package ru.epam.spring.hometask.dao;

import ru.epam.spring.hometask.domain.User;

public interface UserDAO extends AbstractDao<User> {
	User getByEmail(String email);
}
