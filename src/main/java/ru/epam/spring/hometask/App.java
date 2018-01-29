package ru.epam.spring.hometask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.epam.spring.hometask.service.AuditoriumService;
import ru.epam.spring.hometask.service.BookingService;
import ru.epam.spring.hometask.service.EventService;
import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.UserService;
import ru.epam.spring.hometask.service.*;

public class App {

	private AuditoriumService auditoriumService;
	
	private BookingService bookingService;
	
	private EventService eventService;
	
	private UserService userService;
	
	public BookingService getBookingService() {
		return bookingService;
	}

	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAuditoriumService(AuditoriumService auditoriumService) {
		this.auditoriumService = auditoriumService;
	}

	private void run() {
		User user1 = userService.save(
				new User("FirstName1",
						"LastName1",
						"FirstName1_LastName1@epam.com",
				LocalDate.of(1980, Month.MAY, 10)));

		User user2 = userService.save(
				new User("FirstName2",
						"LastName2",
						"FirstName2_LastName2@epam.com",
						LocalDate.of(2000, Month.MAY, 1)));

		Auditorium auditorium1 = auditoriumService.getByName("Auditorium 1");
		Auditorium auditorium2 = auditoriumService.getByName("Auditorium 2");
		Auditorium auditorium3 = auditoriumService.getByName("Auditorium 3");

		Event event1 = eventService.save(new Event("Event 01", 300.0, EventRating.HIGH));
		Event event2 = eventService.save(new Event("Event 02", 200.0, EventRating.MID));
		Event event3 = eventService.save(new Event("Event 03", 100.0, EventRating.LOW));

		LocalDateTime at12 = LocalDateTime.of(2016, Month.MAY, 10, 12, 0);
		LocalDateTime at15 = LocalDateTime.of(2016, Month.MAY, 10, 15, 0);
		LocalDateTime at18 = LocalDateTime.of(2016, Month.MAY, 10, 18, 0);
		event1.addAirDateTime(at12, auditorium1);
		event2.addAirDateTime(at15, auditorium1);
		event3.addAirDateTime(at18, auditorium1);
		event2.addAirDateTime(at12, auditorium2);
		event3.addAirDateTime(at15, auditorium2);
		event1.addAirDateTime(at18, auditorium2);
		event3.addAirDateTime(at12, auditorium3);
		event1.addAirDateTime(at15, auditorium3);
		event2.addAirDateTime(at18, auditorium3);

		// букаем билеты на event1 в 12:00
		Set<Ticket> tickets = new HashSet<>();
		tickets.add(new Ticket(user1, event1, at12, 1));
		tickets.add(new Ticket(user1, event1, at12, 2));
		tickets.add(new Ticket(user1, event1, at12, 3));
		tickets.add(new Ticket(user2, event1, at12, 10));
		tickets.add(new Ticket(user2, event1, at12, 11));
		tickets.add(new Ticket(null, event1, at12, 15));
		tickets.add(new Ticket(null, event1, at12, 16));
		tickets.add(new Ticket(null, event1, at12, 18));
		tickets.add(new Ticket(null, event1, at12, 19));

		bookingService.bookTickets(tickets);

		Set<Ticket> purchasedTickets = bookingService.getPurchasedTicketsForEvent(event1, at12);
		System.out.println(purchasedTickets);

		tickets = new HashSet<>();
		tickets.add(new Ticket(user1, event3, at15, 10));
		tickets.add(new Ticket(user1, event3, at15, 20));
		tickets.add(new Ticket(user2, event3, at15, 21));
		tickets.add(new Ticket(null, event3, at15, 45));
		tickets.add(new Ticket(null, event3, at15, 46));

		bookingService.bookTickets(tickets);

		purchasedTickets = bookingService.getPurchasedTicketsForEvent(event3, at15);
		System.out.println(purchasedTickets);

		purchasedTickets = bookingService.getPurchasedTicketsForEvent(event2, at18);
		System.out.println(purchasedTickets);
		
		double sum = bookingService.getTicketsPrice(event2, at12, user1,
						LongStream.range(26, 51).boxed().collect(Collectors.toSet()));
		System.out.println(sum);
	}

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx =
				new ClassPathXmlApplicationContext("spring.xml");
		App app = (App)ctx.getBean("app");
		
		app.run();
		
		ctx.close();

	}

}
