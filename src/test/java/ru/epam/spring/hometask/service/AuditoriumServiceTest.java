package ru.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.epam.spring.hometask.domain.Auditorium;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class AuditoriumServiceTest {
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Test
	public void testGetAll() {
		assertEquals(3, auditoriumService.getAll().size());
	}
	
	@Test
	public void testGetByName() {

		Auditorium auditorium1 =  auditoriumService.getByName("Auditorium 1");
		assertEquals("Auditorium 1", auditorium1.getName());
		assertEquals(20, auditorium1.getNumberOfSeats());
		assertArrayEquals(new Long[]{14L, 15L, 16L, 17L }, auditorium1.getVipSeats().toArray(new Long[0]));
		
		Auditorium auditorium2 =  auditoriumService.getByName("Auditorium 2");
		assertEquals("Auditorium 2", auditorium2.getName());
		assertEquals(50, auditorium2.getNumberOfSeats());
		assertArrayEquals(new Long[]{43L,44L,45L,46L,47L}, auditorium2.getVipSeats().toArray(new Long[0]));
		
		Auditorium auditorium3 =  auditoriumService.getByName("Auditorium 3");
		assertEquals("Auditorium 3", auditorium3.getName());
		assertEquals(200, auditorium3.getNumberOfSeats());
		assertArrayEquals(new Long[]{184L,185L,186L,187L,188L,189L,190L,191L,192L,193L,194L,195L}, auditorium3.getVipSeats().toArray(new Long[0]));
	}
}
