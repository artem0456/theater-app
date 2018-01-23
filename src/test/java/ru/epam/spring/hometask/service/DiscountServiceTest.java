package ru.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@DirtiesContext
public class DiscountServiceTest {
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
	
	@Test
	public void testDiscountService() {
		
		User user1 = userService.save(
				new User("FirstName1",
						"LastName1",
						"FirstName1_LastName1@epam.com",
						LocalDate.of(1980, Month.MAY, 8)));

		User user2 = userService.save(
				new User("FirstName2",
						"LastName2",
						"FirstName2_LastName2@epam.com",
						null));

		User user3 = userService.save(
				new User("FirstName3",
						"LastName3",
						"FirstName3_LastName3@epam.com",
						LocalDate.of(2000, Month.MAY, 1)));
				
				
		Event event1 = eventService.save(
				new Event("Event 01",
						100.0, EventRating.HIGH));
		
		LocalDateTime at12 = LocalDateTime.of(2016, Month.MAY, 10, 12, 0);
		
		byte discount = discountService.getDiscount(user1, event1, at12, 2);
		assertEquals(5, discount);
		
		discount = discountService.getDiscount(user2, event1, at12, 2);
		assertEquals(0, discount);
		
		discount = discountService.getDiscount(user3, event1, at12, 2);
		assertEquals(0, discount);
		
		discount = discountService.getDiscount(user2, event1, at12, 10);
		assertEquals(5, discount);
		
		discount = discountService.getDiscount(user3, event1, at12, 25);
		assertEquals(4, discount);
		
		discount = discountService.getDiscount(null, event1, at12, 25);
		assertEquals(4, discount);
		
		discount = discountService.getDiscount(user1, event1, at12, 25);
		assertEquals(5, discount);
		
		Set<Ticket> tickets = new HashSet<>();
		tickets.add(new Ticket(user3, event1, at12, 1));
		tickets.add(new Ticket(user3, event1, at12, 2));
		tickets.add(new Ticket(user3, event1, at12, 3));
		tickets.add(new Ticket(user3, event1, at12, 4));
		tickets.add(new Ticket(user3, event1, at12, 5));
		bookingService.bookTickets(tickets);
		
		discount = discountService.getDiscount(user3, event1, at12, 25);
		assertEquals(6, discount);
		
		tickets = new HashSet<>();
		tickets.add(new Ticket(user3, event1, at12, 6));
		tickets.add(new Ticket(user3, event1, at12, 7));
		tickets.add(new Ticket(user3, event1, at12, 8));
		tickets.add(new Ticket(user3, event1, at12, 9));
		tickets.add(new Ticket(user3, event1, at12, 10));
		bookingService.bookTickets(tickets);
		
		discount = discountService.getDiscount(user3, event1, at12, 25);
		assertEquals(4, discount);

	}
}
