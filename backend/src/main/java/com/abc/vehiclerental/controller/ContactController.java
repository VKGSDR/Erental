package com.abc.vehiclerental.controller;

import java.util.List;

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

import com.abc.vehiclerental.entity.ContactUsDetails;
import com.abc.vehiclerental.service.ContactUsService;

@RestController
@CrossOrigin(origins="http://localhost:8080/")
@RequestMapping("/contactdetails")
public class ContactController {

	@Autowired
	private ContactUsService contactUsService;

	@PostMapping("/save/{role}")
	public ResponseEntity<String> saveContact(@PathVariable("role") String role,
			@Valid @RequestBody ContactUsDetails contact) {
		contactUsService.addContact(role, contact);
		return new ResponseEntity<>("saved Contact Details: " + contact.getId(), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ContactUsDetails>> getAllContact() {
		List<ContactUsDetails> contactDetailList = contactUsService.fetchAllContactDetails();
		return new ResponseEntity<>(contactDetailList, HttpStatus.OK);
	}

	@PutMapping("/update/{role}")
	public ResponseEntity<String> updatingContact(@PathVariable("role") String role,
			@RequestBody ContactUsDetails contact) {
		contactUsService.updateContact(role, contact);
		return new ResponseEntity<>("Contact Details Updated successfully. ", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{role}/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable("role") String role, @PathVariable("id") long id) {
		contactUsService.deleteContact(role, id);
		return new ResponseEntity<>("Contact Detail deleted Successfully", HttpStatus.OK);
	}

}
