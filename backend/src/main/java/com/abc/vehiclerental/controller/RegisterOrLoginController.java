package com.abc.vehiclerental.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.vehiclerental.entity.UserDetails;
import com.abc.vehiclerental.payload.LoginPayLoad;
import com.abc.vehiclerental.service.UserService;

//@WebServlet("/caruser")
@CrossOrigin(origins="http://localhost:8081/") //3000 for starting default react application
@RestController
@RequestMapping("/user")
public class RegisterOrLoginController /*extends HttpServlet*/ {

	@Autowired
	UserService userService;

// method for registering user
	/**
	 * 
	 * @param userDetails
	 * @return
	 */
	
	@PostMapping("/register")
	public ResponseEntity<UserDetails> register(@Valid @RequestBody UserDetails userDetails) {
		UserDetails user = userService.registerUser(userDetails);
		return new ResponseEntity<>( user, HttpStatus.CREATED);
	}

//login user method
	@PostMapping("/login")
	public ResponseEntity<UserDetails> login(@Valid @RequestBody LoginPayLoad loginPayLoad) {

		UserDetails user = userService.login(loginPayLoad.getEmailId(), loginPayLoad.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

//logouts user
	@PostMapping("/logout/{mailid}")
	public ResponseEntity<String> logout(@Valid @PathVariable("mailid") String emailId) {

		UserDetails user = userService.logout(emailId);
		return new ResponseEntity<>("successfully logged out with userId: " + user.getUserId(), HttpStatus.OK);
	}

//fetch user by userId
	@GetMapping("/getuserdetails/{id}")
	public ResponseEntity<UserDetails> getUserDetails(@Valid @PathVariable("id") long userId) {
		UserDetails userDetails = userService.getUser(userId);
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

//fetches all users in the user_details table
	@GetMapping("/getall")
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		List<UserDetails> usersList = userService.getAllUsers();
		return new ResponseEntity<>(usersList, HttpStatus.OK);
	}

//user updation method
	@PutMapping("/update")
	public ResponseEntity<String> updatingUser(@Valid @RequestBody UserDetails user) {
		userService.updateUser(user);
		return new ResponseEntity<>("Updated user with id: " + user.getUserId(), HttpStatus.CREATED);
	}

//deleting user 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@Valid @PathVariable("id") long userId) {
		userService.deleteAccount(userId);
		return new ResponseEntity<>("User A/c deleted Successfully.", HttpStatus.OK);
	}

}
