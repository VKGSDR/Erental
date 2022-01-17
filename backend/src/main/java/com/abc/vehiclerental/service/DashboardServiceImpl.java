package com.abc.vehiclerental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.repository.DashboardRepository;
import com.abc.vehiclerental.repository.GuestFeedbackRepository;
import com.abc.vehiclerental.repository.RegisteredFeedbackRepository;
import com.abc.vehiclerental.repository.UserRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GuestFeedbackRepository guestFeedbackRepository;

	@Autowired
	private RegisteredFeedbackRepository registeredFeedbackRepository;

	@Override
	public int getAllBookedCars() {
		return dashboardRepository.findByIsBooked();
	}

	@Override
	public int getTotalCars() {
		return dashboardRepository.findByCar();
	}

	@Override
	public int getTotalRegisteredUsers() {
		return userRepository.findByRole();
	}

	@Override
	public int getTotalSubscriber() {
		return userRepository.findByIsSubscriber();
	}

	@Override
	public int getTotalQuery() {
		return guestFeedbackRepository.findByQuery() + registeredFeedbackRepository.findByQuery();
	}

}
