package com.abc.vehiclerental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.vehiclerental.entity.GuestFeedback;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.GuestFeedbackRepository;

/**
 * @author shrzore
 *
 */
@Service
public class GuestFeedbackServiceImpl implements GuestFeedbackService {

	@Autowired
	private GuestFeedbackRepository guestFeedbackRepository;

	/**
	 * Saves the given guest
	 *
	 * @param guest details-name,email,mobile,query
	 * @return guest details
	 */
	@Override
	public GuestFeedback addQuery(GuestFeedback guestFeedback) {

		return guestFeedbackRepository.save(guestFeedback);

	}

	/**
	 * sends feedback based on the query
	 * 
	 * @param queryId-auto-generated id assigned to query
	 * @param feedback-updated       feedback based on queryId
	 */
	@Transactional
	public void updateFeedback(long queryId, String feedback) {
		Optional<GuestFeedback> optionalGuestFeedback = guestFeedbackRepository.findById(queryId);
		if (optionalGuestFeedback.isEmpty()) {
			throw new ResourceNotFoundException("Query not existing with id:" + queryId);
		}
		guestFeedbackRepository.updateFeedback(queryId, feedback);
	}

}
