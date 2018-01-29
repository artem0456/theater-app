package ru.epam.spring.hometask.service.impl;

import java.util.Collection;

import ru.epam.spring.hometask.dao.UserDAO;
import ru.epam.spring.hometask.service.UserService;
import ru.epam.spring.hometask.domain.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User object) {
        return userDAO.save(object);
    }

    @Override
    public void remove(User object) {
        userDAO.remove(object);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

}
