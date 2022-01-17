package com.abc.vehiclerental.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.entity.BookingDetails;
import com.abc.vehiclerental.entity.RegisteredFeedback;
import com.abc.vehiclerental.entity.UserDetails;
import com.abc.vehiclerental.exception.AuthenticationFailureException;
import com.abc.vehiclerental.exception.ResourceAlreadyExistingException;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.BookingRepository;
import com.abc.vehiclerental.repository.RegisteredFeedbackRepository;
import com.abc.vehiclerental.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired 
	private RegisteredFeedbackRepository feedbackRepository;

	/*
	 * This registerUser method initially checks emailId whether it is existing or
	 * not ,if exists throws exception or saves userDetails
	 */
	@Override
	public UserDetails registerUser(UserDetails userDetails) {
		UserDetails optionalAccount = userRepository.findByEmailId(userDetails.getEmailId());
		if (optionalAccount != null) {
			throw new ResourceAlreadyExistingException(
					"Account is already existing with emailId: " + userDetails.getEmailId());
		}

		return userRepository.save(userDetails);
	}

	/*
	 * login query of the userRepository will get called and fetches the table .if
	 * user is null throws exception else sets loggedIn flag to true indicating it
	 * is logged in
	 */
	@Override
	public UserDetails login(String emailId, String password) {
		UserDetails user = userRepository.login(emailId, password);

		if (user == null) {
			throw new AuthenticationFailureException("unable to login");
		} else {
			user.setLoggedIn(true);
		}
		return user;
	}

	/*
	 * Fetches user by emailId,if user is not null and loggedIn flag is true then it
	 * sets flag to false and save repository
	 */
	@Override
	public UserDetails logout(String emailId) {
		UserDetails user = userRepository.findByEmailId(emailId);
		if (user == null) {
			throw new ResourceNotFoundException("emailId not existing");
		} else if (user.isLoggedIn() == true) {
			user.setLoggedIn(false);
			userRepository.save(user);
		}

		return user;
	}

	// fetches user by userId and if it is a null throws exception
	@Override
	public UserDetails getUser(long userId) {
		Optional<UserDetails> user = userRepository.findById(userId);
		if (user == null) {
			throw new ResourceNotFoundException("no records found");
		}
		UserDetails userDetails = user.get();
		return userDetails;
	}

	// fetches all the users by findAll method of the Jparepository
	@Override
	public List<UserDetails> getAllUsers() {
		List<UserDetails> usersList = userRepository.findAll();
		return usersList;
	}

//if any modification need to be done for userDetails this method is executed
	@Override
	public void updateUser(UserDetails user) {
		Optional<UserDetails> optionalAccount = userRepository.findById(user.getUserId());
		if (optionalAccount.isEmpty()) {
			throw new ResourceNotFoundException("user is not existing with id: " + user.getUserId());
		}
		userRepository.save(user);
	}

//deletes account of user from the repository
	@Override
	public void deleteAccount(long userId) {
		Optional<UserDetails> optionalAccount = userRepository.findById(userId);
		if (optionalAccount.isEmpty()) {
			throw new ResourceNotFoundException("user is not existing with id: " + userId);
		}
		UserDetails userDetails=optionalAccount.get();
		List<BookingDetails> userBookings= userDetails.getBookings();
		for(BookingDetails b:userBookings) {
			bookingRepository.delete(b);
			
		}
		
		List<RegisteredFeedback> userFeedback= userDetails.getRegisteredFeedback();
		for(RegisteredFeedback f:userFeedback) {
			feedbackRepository.delete(f);
			//userFeedback.remove(f);
		}
		userRepository.deleteById(userId);
	}

}
