package ru.epam.spring.hometask.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.DiscountService;
import ru.epam.spring.hometask.service.discount.DiscountStrategy;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> strategies;

    private DiscountServiceImpl(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public byte getDiscount(User user, Event event,
                            LocalDateTime airDateTime, long numberOfTickets) {

        byte discount = 0;

        for(DiscountStrategy strategy : strategies) {
            byte d = strategy.getDiscount(user, event, airDateTime, numberOfTickets);
            discount = d > discount ? d : discount;
        }
        return discount;
    }

}
