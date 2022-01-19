package com.abc.vehiclerental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.vehiclerental.entity.RegisteredFeedback;
import com.abc.vehiclerental.payload.FeedbackPayload;
import com.abc.vehiclerental.service.RegisteredFeedbackService;

@RestController
@CrossOrigin(origins="http://localhost:8080/")
@RequestMapping("/registeredfeedback")
public class RegisteredFeedbackController {

	@Autowired
	RegisteredFeedbackService registeredFeedbackService;

	@PostMapping("/addquery/{userId}")
	public ResponseEntity<RegisteredFeedback> addQuery(@PathVariable("userId") long userId,
			@RequestBody RegisteredFeedback registeredFeedback) {
		RegisteredFeedback registeredFeedback1 = registeredFeedbackService.addQuery(userId, registeredFeedback);
		return new ResponseEntity<>(registeredFeedback1, HttpStatus.CREATED);

	}

	@PutMapping("/updatefeedback")
	public ResponseEntity<String> update(@RequestBody FeedbackPayload feedbackPayload) {
		registeredFeedbackService.updateFeedback(feedbackPayload.getQueryId(), feedbackPayload.getFeedback());
		return new ResponseEntity<>("Feedback sent successfully", HttpStatus.CREATED);
	}

}
  
