package ru.epam.spring.hometask.service.dicount.impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.dicount.Discount;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class LuckyDiscountImpl implements Discount {

    private static final byte LUCKY_DISCOUNT = 50;
    private static final long EACH_TICKET = 10;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                              @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        if (user.isRegistered()) {
            return numberOfTickets % EACH_TICKET == 0 ? LUCKY_DISCOUNT : 0;
        } else {
            long numberOfUserTickets = user.getTickets().size() + numberOfTickets;
            return numberOfUserTickets % EACH_TICKET == 0 ? LUCKY_DISCOUNT : 0;
        }
    }
}
