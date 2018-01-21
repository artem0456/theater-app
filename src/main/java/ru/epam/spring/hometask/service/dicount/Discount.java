package ru.epam.spring.hometask.service.dicount;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public interface Discount {

    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                              @Nonnull LocalDateTime airDateTime, long numberOfTickets);
}
