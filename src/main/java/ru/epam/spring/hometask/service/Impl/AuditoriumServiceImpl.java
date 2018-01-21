package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {

    private Map<String, Auditorium> auditoriums;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
     return (Set<Auditorium>) auditoriums.values();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriums.get(name);
    }

//    public Map<String, Auditorium> getAuditoriums() {
//        return auditoriums;
//    }

    public void setAuditoriums(Map<String, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}

