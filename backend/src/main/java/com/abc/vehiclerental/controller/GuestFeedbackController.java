package com.abc.vehiclerental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.vehiclerental.entity.GuestFeedback;
import com.abc.vehiclerental.payload.FeedbackPayload;
import com.abc.vehiclerental.service.GuestFeedbackService;

@RestController
@CrossOrigin(origins="http://localhost:808/")
@RequestMapping("/guestfeedback")
public class GuestFeedbackController {

	@Autowired
	private GuestFeedbackService guestFeedbackService;

	@PostMapping("/addquery")
	public ResponseEntity<String> addQuery(@Valid @RequestBody GuestFeedback guestFeedback) {
		guestFeedbackService.addQuery(guestFeedback);
		return new ResponseEntity<>("Query sent successfully", HttpStatus.CREATED);

	}

	@PutMapping("/updatefeedback")
	public ResponseEntity<String> update(@RequestBody FeedbackPayload feedbackPayload) {
		guestFeedbackService.updateFeedback(feedbackPayload.getQueryId(), feedbackPayload.getFeedback());
		return new ResponseEntity<>("Feedback sent successfully", HttpStatus.CREATED);
	}

}
