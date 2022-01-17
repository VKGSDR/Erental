package com.abc.vehiclerental.controller;

import java.util.List;
import java.util.Optional;

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

import com.abc.vehiclerental.entity.BookingDetails;
import com.abc.vehiclerental.payload.BookingPayload;
import com.abc.vehiclerental.payload.PaymentPayload;
import com.abc.vehiclerental.service.BookingService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/bookdetails")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/savebook")
	public ResponseEntity<String> saveBookDetails(@RequestBody BookingPayload bookingPayload) {
		BookingDetails bookingDetails = bookingService.addBookingDetails(bookingPayload.getUserId(),
				bookingPayload.getCarId());
		return new ResponseEntity<>("Saved booking details  with id: " + bookingDetails.getBookingId(),
				HttpStatus.CREATED);

	}
//
	@PutMapping("/updatebook")
	public ResponseEntity<String> updateBookDetails(@RequestBody PaymentPayload paymentPayload) {

		BookingDetails updateDetails = bookingService.updateBooking(paymentPayload.getUserBookingId(),
				paymentPayload.getNoOfDays());
		return new ResponseEntity<>("updating booking details  with id: " + updateDetails.getBookingId(),
				HttpStatus.CREATED);

	}

	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") long bookingId) {
		bookingService.deleteBooking(bookingId);
		return new ResponseEntity<>("Booking details deleted Successfully with id :" + bookingId, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookingDetails>> getAllBookingDetails() {
		List<BookingDetails> bookingDetailList = bookingService.fetchAllBookingDetails();
		return new ResponseEntity<>(bookingDetailList, HttpStatus.OK);
	}

	@GetMapping("/searchBookById/{id}")
	public ResponseEntity<BookingDetails> serachBookingById(@PathVariable("id") long bookingId) {
		Optional<BookingDetails> bookListById = bookingService.searchBookingById(bookingId);
		return new ResponseEntity<>(bookListById.get(),HttpStatus.OK);
	}

}
