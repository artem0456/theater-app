package ru.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@DirtiesContext
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserService() {
		
		User savedUser1 = userService.save(
				new User("FirstName1",
						"LastName1",
						"FirstName1_LastName1@epam.com",
						LocalDate.of(2000, Month.MAY, 1)));
		assertEquals(new Long(0L), savedUser1.getId());

		User savedUser2 = userService.save(new User("FirstName2",
				"LastName2",
				"FirstName2_LastName2@epam.com",
				null));
		assertEquals(new Long(1L), savedUser2.getId());

		User savedUser3 = userService.save(
				new User("FirstName3",
						"LastName3",
						"FirstName3_LastName3@epam.com",
						LocalDate.of(1980, Month.MAY, 10)));
		assertEquals(new Long(2L), savedUser3.getId());
		
		assertEquals(3, userService.getAll().size());
		
		userService.remove(savedUser2);
		assertEquals(2, userService.getAll().size());
		
		savedUser2 = userService.save(savedUser2);
		assertEquals(3, userService.getAll().size());
		
		savedUser2.setLastName("LastName33");
		savedUser2 = userService.save(savedUser2);
		User updatedUser = userService.getById(savedUser2.getId());
		assertEquals("LastName33", updatedUser.getLastName());
		
		User foundUser = userService.getUserByEmail("FirstName3_LastName3@epam.com");
		assertEquals(foundUser, savedUser3);
		
	}
}
