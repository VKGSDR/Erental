package com.abc.vehiclerental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.vehiclerental.entity.RegisteredFeedback;
import com.abc.vehiclerental.entity.UserDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.RegisteredFeedbackRepository;
import com.abc.vehiclerental.repository.UserRepository;

/**
 * @author shrzore
 *
 */

@Service
public class RegisteredFeedbackServiceImpl implements RegisteredFeedbackService {

	@Autowired
	private RegisteredFeedbackRepository registeredFeedbackRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * stores the query for registered user
	 * 
	 * @param userId-auto-generated   id assigned to registered user
	 * @param registeredFeedback-user is adding query based on userId
	 *
	 */
	public RegisteredFeedback addQuery(long userId, RegisteredFeedback registeredFeedback) {

		/*Optional<UserDetails> optionalUserDetails = userRepository.findById(userId);
		if (optionalUserDetails.isEmpty()) {
			throw new ResourceNotFoundException("User not existing with id:" + userId);
		}

	//	registeredFeedback.setUser(optionalUserDetails.get());
		return registeredFeedbackRepository.save(registeredFeedback);
*/
		return registeredFeedback;
	}

	/**
	 * updates the feedback for registered user
	 * 
	 * @param queryId-auto-generated id assigned to query
	 * @param feedback-updated       feedback based on queryId
	 */
	@Transactional
	public void updateFeedback(long queryId, String feedback) {
		Optional<RegisteredFeedback> optionalRegisteredFeedback = registeredFeedbackRepository.findById(queryId);
		if (optionalRegisteredFeedback.isEmpty()) {
			throw new ResourceNotFoundException("Query not existing with id:" + queryId);
		}
		registeredFeedbackRepository.updateFeedback(queryId, feedback);
	}

}
