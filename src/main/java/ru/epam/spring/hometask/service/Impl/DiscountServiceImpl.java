package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class DiscountServiceImpl implements DiscountService {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return 0;
    }
}
