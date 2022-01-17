package com.abc.vehiclerental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.vehiclerental.entity.UserDetails;
import com.abc.vehiclerental.exception.AuthenticationFailureException;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.UserRepository;
import com.abc.vehiclerental.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Test
	void testGetUser() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(100L);
		userDetails.setUserName("Shalini");
		userDetails.setMobileNo("8867784779");
		userDetails.setAddress("mysore");
		userDetails.setEmailId("shalini@tmail.com");

		Optional<UserDetails> optionalUserDetails = Optional.of(userDetails);

		when(userRepository.findById(100L)).thenReturn(optionalUserDetails);

		UserDetails userDetails1 = userServiceImpl.getUser(100L);

		assertEquals(userDetails.getUserId(), userDetails1.getUserId());
		assertEquals(userDetails.getUserName(), userDetails1.getUserName());
		assertEquals(userDetails.getMobileNo(), userDetails1.getMobileNo());
		assertEquals(userDetails.getAddress(), userDetails1.getAddress());

	}

	@Test
	void testGetUserByIdThrowingException() {

		when(userRepository.findById(120L)).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUser(120L));
	}

	@Test
	void testLogin() {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailId("shalini@tmail.com");
		userDetails.setPassword("Shalini@123");

		// UserDetails optionalUserDetails = Optional.of(userDetails);

		when(userRepository.login("shalini@tmail.com", "Shalini@123")).thenReturn(userDetails);

		UserDetails userDetails1 = userServiceImpl.login("shalini@tmail.com", "Shalini@123");

		assertEquals(userDetails.getEmailId(), userDetails1.getEmailId());
	}

	@Test
	void testLoginThrowingException() {

		when(userRepository.login("shalini@tmail.com", "Shalini@123")).thenThrow(AuthenticationFailureException.class);

		assertThrows(AuthenticationFailureException.class,
				() -> userServiceImpl.login("shalini@tmail.com", "Shalini@123"));
	}

	@Test
	void testLogout() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(100L);
		userDetails.setEmailId("shalini@tmail.com");

		// UserDetails optionalUserDetails = Optional.of(userDetails);

		when(userRepository.findByEmailId("shalini@tmail.com")).thenReturn(userDetails);

		UserDetails userDetails1 = userServiceImpl.logout("shalini@tmail.com");

		assertEquals(userDetails.getEmailId(), userDetails1.getEmailId());
		assertEquals(userDetails.getUserId(), userDetails1.getUserId());
	}

	@Test
	void testLogoutThrowingException() {

		when(userRepository.findByEmailId("shalinooi@tmail.com")).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.logout("shalinooi@tmail.com"));
	}

}
