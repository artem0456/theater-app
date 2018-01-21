package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {
    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return null;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return null;
    }
}
