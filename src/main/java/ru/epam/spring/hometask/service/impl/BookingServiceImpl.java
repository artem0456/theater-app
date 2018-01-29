package ru.epam.spring.hometask.service.impl;

import ru.epam.spring.hometask.dao.TicketDAO;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.BookingService;
import ru.epam.spring.hometask.service.DiscountService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    private TicketDAO ticketDAO;

    private DiscountService discountService;

    private double vipRate;

    private Map<EventRating, Double> ratingRates;

    private BookingServiceImpl(double vipRate, Map<EventRating, Double> ratingRates) {
        this.vipRate = vipRate;
        this.ratingRates = ratingRates;
    }

    @Override
    public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) {

        double sum = 0.0;
        double base = event.getBasePrice();
        double ratingRate = ratingRates.get(event.getRating());
        double discount = discountService.getDiscount(user, event, dateTime, seats.size());

        Set<Long> vipSeats = new HashSet<>(seats);
        vipSeats.retainAll(event.getAuditoriums().get(dateTime).getVipSeats());

        int vipSeatsNumber =  vipSeats.size();
        int nonVipSeatCount = seats.size() - vipSeatsNumber;

        sum = base * ratingRate * (nonVipSeatCount + vipRate * vipSeatsNumber);
        sum = (100 - discount) * sum / 100;

        return sum;
    }

    @Override
    public void bookTickets(Set<Ticket> tickets) {
        for(Ticket ticket : tickets ) {
            ticketDAO.save(ticket);
        }
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return ticketDAO.getByEventByDateTime(event, dateTime);
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
