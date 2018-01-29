package ru.epam.spring.hometask.service.discount;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    public byte getDiscount(User user, Event event,
                              LocalDateTime airDateTime, long numberOfTickets);


}
