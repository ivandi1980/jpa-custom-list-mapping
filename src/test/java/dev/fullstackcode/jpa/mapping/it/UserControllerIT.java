package dev.fullstackcode.jpa.mapping.it;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import dev.fullstackcode.jpa.mapping.entity.User;
import dev.fullstackcode.jpa.mapping.entity.UserType;

public class UserControllerIT extends BaseIT {

	@Test
	@Sql({ "/DELETE_ALL.sql" })
	public void saveUser() {

		User user = new User();

		user.setFirstName("abc");
		user.setLastName("def");
		user.setDisabled(false);
		user.setSalary(1200d);
		user.setUserType(UserType.GOLD);
		String ph1 = "+642109345667";
		String ph2 = "+642109345668";
		List<String> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(ph1);
		phoneNumbers.add(ph2);

		user.setPhoneNumbers(phoneNumbers);

		String[] roles = new String[]{"ROLE_SERVICE_USER","ROLE_MANAGER","ROLE_CLIENT_ADMIN","ROLE_SYS_ADMIN"};
		user.setRoles(roles);

		String[] emails = new String[] {"test@test.com","test1@test.com"};
		user.setEmails(emails);

		List<String> hobbies = Arrays.asList("Swimming","Jogging");
		user.setHobbies(hobbies);

		List<String> preferences = Arrays.asList("cookie","performance");
		user.setPreferences(preferences);


		List<String> orgPreferences = Arrays.asList("X1","X2");
		user.setOrgPreferences(orgPreferences);

		ResponseEntity<User> response = testRestTemplate.postForEntity( "/users", user, User.class);

		User u = (User) response.getBody();

		assertNotNull(u.getId());
		assertEquals("abc", u.getFirstName());
		assertEquals(phoneNumbers, u.getPhoneNumbers());
		assertArrayEquals(roles,u.getRoles());
		assertArrayEquals(emails,u.getEmails());
		assertEquals(hobbies, u.getHobbies());
		assertEquals(orgPreferences, u.getOrgPreferences());


	}

	@Test
	@Sql({ "/DELETE_ALL.sql", "/INSERT_USERS.sql" })
	public void testGetUserById() {
		long userId = 104;

		ResponseEntity<User> responseEntity = testRestTemplate.getForEntity("/users/{id}", User.class,
				userId);
		String[] roles = new String[]{"ROLE_SERVICE_USER","ROLE_MANAGER","ROLE_CLIENT_ADMIN","ROLE_SYS_ADMIN"};
		User u = responseEntity.getBody();

		assertEquals(104, u.getId());

		assertEquals(Arrays.asList("+642109345667, +642109345668".split(",")),u.getPhoneNumbers());
		assertArrayEquals(roles,u.getRoles());

	}

}
