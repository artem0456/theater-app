package ru.epam.spring.hometask.service.discount.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.discount.DiscountStrategy;

public class BirthdayDiscountStrategy implements DiscountStrategy {

    private byte discount;

    private BirthdayDiscountStrategy(byte discount) {
        this.discount = discount;
    }

    @Override
    public byte getDiscount(User user, Event event,
                            LocalDateTime airDateTime, long numberOfTickets) {
        if( user != null && user.getBirthday() != null ) {
            LocalDate birthDate = user.getBirthday();
            birthDate = birthDate.plusYears(airDateTime.getYear() - birthDate.getYear());

            if ( Math.abs(airDateTime.getDayOfYear() - user.getBirthday().getDayOfYear()) <= 5 ) {

                return discount;
            }
        }
        return 0;
    }

}
