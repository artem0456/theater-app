package ru.epam.spring.hometask.service.discount.impl;

import ru.epam.spring.hometask.dao.TicketDAO;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.discount.DiscountStrategy;

import java.time.LocalDateTime;

public class Every10thDiscountStrategy implements DiscountStrategy {

    private byte discount;

    private TicketDAO ticketDAO;

    private Every10thDiscountStrategy(byte discount, TicketDAO ticketDAO) {
        this.discount = discount;
        this.ticketDAO = ticketDAO;
    }

    @Override
    public byte getDiscount(User user, Event event,
                            LocalDateTime airDateTime, long numberOfTickets) {

        long prevTickets = 0;
        if(user != null) {
            prevTickets = ticketDAO.getAllForUser(user).size();
        }
        long every10ths = (prevTickets + numberOfTickets)/10 - prevTickets / 10;
        if(every10ths > 0) {
            return (byte) Math.floor(100 - 100.0 *
                    (numberOfTickets - discount / 100.0 * every10ths) / numberOfTickets);
        }
        return 0;
    }

}
