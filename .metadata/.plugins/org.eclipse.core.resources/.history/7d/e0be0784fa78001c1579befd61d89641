package com.abc.vehiclerental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.vehiclerental.payload.DashboardPayload;
import com.abc.vehiclerental.service.DashboardService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/dashboarddetails")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@GetMapping("/getAlldashboarddetails")
	public ResponseEntity<DashboardPayload> getAllDashboarddetails() {

		int countOfBookedCars = dashboardService.getAllBookedCars();
		int countOfTotalCars = dashboardService.getTotalCars();
		int countOfRegisteredUsers = dashboardService.getTotalRegisteredUsers();
		int countOfSubscribers = dashboardService.getTotalSubscriber();
		int countOfQueries = dashboardService.getTotalQuery();

		DashboardPayload dashboardPayload = new DashboardPayload();

		dashboardPayload.setNoOfBookedCars(countOfBookedCars);
		dashboardPayload.setNoOfCars(countOfTotalCars);
		dashboardPayload.setNoOfRegisteredUsers(countOfRegisteredUsers);
		dashboardPayload.setNoOfSubscribers(countOfSubscribers);
		dashboardPayload.setNoOfQuery(countOfQueries);

		return new ResponseEntity<>(dashboardPayload, HttpStatus.OK);
	}
}
