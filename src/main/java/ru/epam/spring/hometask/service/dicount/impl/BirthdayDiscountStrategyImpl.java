package ru.epam.spring.hometask.service.dicount.impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.dicount.Discount;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class BirthdayDiscountStrategyImpl implements Discount {

    private static final byte BIRTHDAY_DISCOUNT = 5;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                              @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        LocalDate dateAirDate = LocalDate.of(0, airDateTime.getMonthValue(),
                airDateTime.getDayOfMonth());
        LocalDate birthdayUser  = LocalDate.of(0, user.getBirthday().getMonth(),
                user.getBirthday().getDayOfMonth() );

        int diff = Period.between(dateAirDate,birthdayUser).getDays();
        return (0 >= diff && diff <= 5) ? BIRTHDAY_DISCOUNT : 0;

    }
}
