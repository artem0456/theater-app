package ru.epam.spring.hometask.service.Impl;

import ru.epam.spring.hometask.domain.*;
import ru.epam.spring.hometask.service.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.Set;

public class BookingServiceImpl implements BookingService {


    private static final double RATE_FOR_HIGHT_EVENT_RAITING = 1.2;
    private static final double RATE_VIP_SEAT = 2;

    private DiscountService discountService;
    private AuditoriumService auditoriumService;
    private EventService eventService;
    private UserService userService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime,
                                  @Nullable User user, @Nonnull Set<Long> seats) {

        double discount = discountService.getDiscount(user, event, dateTime, seats.size())/100.00;
        double basePrice = event.getBasePrice();
        double totalPrice = 0;
        boolean isSeatVip;

        Event eventById;
        Auditorium auditorium;

        if (event.getRating() == EventRating.HIGH) {
            basePrice = basePrice * RATE_FOR_HIGHT_EVENT_RAITING;
        }
        basePrice += basePrice * discount;


        for (Long id : seats) {
            Long eventId = event.getId();
            eventById = eventService.getById(eventId);
            auditorium = eventById.getAuditoriums().get(dateTime);
            isSeatVip = auditoriumService.getByName(auditorium.getName()).isSeatVip(id);
            if (isSeatVip) {
                totalPrice += basePrice * RATE_VIP_SEAT;
            } else {
                totalPrice += basePrice;
            }
        }
        return totalPrice;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        long eventId, userId;

        User userOfTicket;
        Event event;

        for (Ticket ticket : tickets) {
            eventId = ticket.getEvent().getId();
            event = eventService.getById(eventId);
            event.getAuditoriums().get(ticket.getDateTime()).addTicket(ticket);

            userOfTicket = ticket.getUser();
            userId = userOfTicket.getId();

            if (userService.getById(userId) != null) {
                userOfTicket.getTickets().add(ticket);
                userService.save(userOfTicket);
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                   @Nonnull LocalDateTime dateTime) {

        Event choosenEvent = eventService.getById(event.getId());
        NavigableMap<LocalDateTime, Auditorium> auditoriums = choosenEvent.getAuditoriums();
        Auditorium auditorium = auditoriums.get(dateTime);
        return auditorium.getTickets();
    }
}

