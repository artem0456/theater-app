package ru.epam.spring.hometask.service.discount.impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.discount.DiscountStrategy;

import java.time.LocalDateTime;

public class BirthdayDiscountStrategy implements DiscountStrategy {

    private byte discount;

    private BirthdayDiscountStrategy(byte discount) {
        this.discount = discount;
    }

    @Override
    public byte getDiscount( User user, Event event,
                             LocalDateTime airDateTime, long numberOfTickets) {

        if( user != null && user.getBirthday() != null ) {

            int diff = Math.abs(airDateTime.getDayOfYear()
                    - user.getBirthday().getDayOfYear());

            if ( diff <= 5 )
                return discount;
        }
        return 0;
    }

}
