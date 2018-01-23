package ru.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@DirtiesContext
public class EventServiceTest {
	
	@Autowired
	private EventService eventService;
	
	@Test
	public void testEventService() {
		
		Event savedEvent1 = eventService.save(new Event("Event 01", 100.0, EventRating.HIGH));
		assertEquals(new Long(0L), savedEvent1.getId());
		
		Event savedEvent2 = eventService.save(new Event("Event 02", 60.0, EventRating.MID));
		assertEquals(new Long(1L), savedEvent2.getId());
		
		Event savedEvent3 = eventService.save(new Event("Event 03", 40.0, EventRating.LOW));
		assertEquals(new Long(2L), savedEvent3.getId());
		
		assertEquals(3, eventService.getAll().size());
		
		eventService.remove(savedEvent2);
		assertEquals(2, eventService.getAll().size());
		
		savedEvent2 = eventService.save(savedEvent2);
		assertEquals(3, eventService.getAll().size());
		
		savedEvent2.setName("Event 02-1");
		savedEvent2 = eventService.save(savedEvent2);
		Event updatedEvent = eventService.getById(savedEvent2.getId());
		assertEquals("Event 02-1", updatedEvent.getName());
		
		Event foundUser = eventService.getByName("Event 03");
		assertEquals(foundUser, savedEvent3);
		
	}
}
