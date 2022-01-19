package com.abc.vehiclerental.controller;

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

import com.abc.vehiclerental.entity.PaymentDetails;
import com.abc.vehiclerental.payload.ConfirmPaymentPayload;
import com.abc.vehiclerental.service.PaymentService;

@RestController
@CrossOrigin(origins="http://localhost:8080/")
@RequestMapping("/paymentdetails")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/savepayment")
	public ResponseEntity<String> savePayment(@RequestBody ConfirmPaymentPayload newPayload) {
		PaymentDetails paymentDetails = paymentService.addPayment(newPayload.getBookingId(),
				newPayload.getModeOfPayment());
		return new ResponseEntity<>("Saved payment details  with id: " + paymentDetails.getPaymentId(),
				HttpStatus.CREATED);

	}

	@PutMapping("/updatepayment")
	public ResponseEntity<String> updatePayment(@RequestBody ConfirmPaymentPayload newPayload) {
		PaymentDetails paymentDetails = paymentService.updatePaymentAfterBooking(newPayload.getModeOfPayment(),
				newPayload.getBookingId());
		return new ResponseEntity<>("Updated payment details with id: " + paymentDetails.getPaymentId(),
				HttpStatus.CREATED);

	}

	@DeleteMapping("/deletepayment/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable("id") long paymentId) {
		paymentService.deletePayment(paymentId);
		return new ResponseEntity<>("Payment details deleted Successfully.", HttpStatus.OK);
	}

	@GetMapping("/searchPaymentById/{id}")
	public ResponseEntity<Optional<PaymentDetails>> serachPaymentById(@PathVariable("id") long paymentId) {
		Optional<PaymentDetails> paymentListById = paymentService.fetchPaymentDetailsByID(paymentId);
		return new ResponseEntity<>(paymentListById, HttpStatus.OK);
	}

}
