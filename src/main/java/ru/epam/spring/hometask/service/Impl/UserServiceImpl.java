package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.dao.UserDao;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class UserServiceImpl implements UserService {

    private UserDao users;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return users.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User object) {
        return users.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        users.remove(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return users.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return users.getAll();
    }

    public UserDao getUsers() {
        return users;
    }

    public void setUsers(UserDao users) {
        this.users = users;
    }
}


