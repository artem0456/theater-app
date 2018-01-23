package ru.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.epam.spring.hometask.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@DirtiesContext
public class BookingServiceTest {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Test
	public void testBookingTikets() {
		
		User user1 = userService.save(new User("FirstName1", "LastName1", "FirstName1_LastName1@epam.com", LocalDate.of(1980, Month.MAY, 10)));
		User user2 = userService.save(new User("FirstName2", "LastName2", "FirstName2_LastName2@epam.com", LocalDate.of(2000, Month.MAY, 1)));
		
		Auditorium auditorium1 =  auditoriumService.getByName("Auditorium 1");
		Auditorium auditorium2 =  auditoriumService.getByName("Auditorium 2");
		Auditorium auditorium3 =  auditoriumService.getByName("Auditorium 3");
		
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
		assertEquals (tickets.size(), purchasedTickets.size());
		
		tickets = new HashSet<>();
		tickets.add(new Ticket(user1, event3, at15, 10));
		tickets.add(new Ticket(user1, event3, at15, 20));
		tickets.add(new Ticket(user2, event3, at15, 21));
		tickets.add(new Ticket(null, event3, at15, 45));
		tickets.add(new Ticket(null, event3, at15, 46));
		
		bookingService.bookTickets(tickets);
		
		purchasedTickets  = bookingService.getPurchasedTicketsForEvent(event3, at15);
		assertEquals (tickets.size(), purchasedTickets.size());
		
		purchasedTickets  = bookingService.getPurchasedTicketsForEvent(event2, at18);
		assertEquals (0, purchasedTickets.size());
	}
	
	@Test
	public void testPriceTikets() {
		
		User user1 = userService.getById(0L);
		User user2 = userService.getById(1L);
		
		LocalDateTime at12 = LocalDateTime.of(2016, Month.MAY, 10, 12, 0);
		
		Event event1 = eventService.getById(0L);
		Event event2 = eventService.getById(1L);
		Event event3 = eventService.getById(2L);
		
		double sum = bookingService.getTicketsPrice(event2, at12, user2, new HashSet<>(Arrays.asList(2L, 3L, 4L)));
		assertEquals(3 * 200.0, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user1, new HashSet<>(Arrays.asList(2L, 3L, 4L)));
		assertEquals(3 * 200.0 * 0.95, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, null, new HashSet<>(Arrays.asList(2L, 3L, 4L)));
		assertEquals(3 * 200.0 , sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event1, at12, user2, new HashSet<>(Arrays.asList(2L, 3L, 4L)));
		assertEquals(3 * 300.0 * 1.2, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event3, at12, user2, new HashSet<>(Arrays.asList(2L, 3L, 4L)));
		assertEquals(3 * 100.0 * 0.8, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user2, new HashSet<>(Arrays.asList(44L, 45L, 46L)));
		assertEquals(3 * 200.0 * 1.5, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user2, new HashSet<>(Arrays.asList(2L, 3L, 4L, 44L, 45L, 46L)));
		assertEquals(200.0 * (3 + 3 * 1.5), sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user2, LongStream.range(1, 11).boxed().collect(Collectors.toSet()));
		assertEquals(10 * 200.0 *0.95, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user2, LongStream.range(26, 51).boxed().collect(Collectors.toSet()));
		assertEquals(200.0 *(20 + 5 * 1.5) * 0.96, sum, 0.0);
		
		sum = bookingService.getTicketsPrice(event2, at12, user1, LongStream.range(26, 51).boxed().collect(Collectors.toSet()));
		assertEquals(200.0 *(20 + 5 * 1.5) * 0.94, sum, 0.0);
	}
}
