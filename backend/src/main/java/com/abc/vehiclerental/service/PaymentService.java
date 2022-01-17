package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import com.abc.vehiclerental.entity.PaymentDetails;

public interface PaymentService {

	// adding payment details
	public PaymentDetails addPayment(long bookingId, String modeOfPayment);

	// updating payment details
	public void updatePayment(PaymentDetails payment);

	// delete payment details
	public void deletePayment(long paymentId);

	// fetch all payment details
	public List<PaymentDetails> fetchAllPaymentDetails();

	// fetch payment details by id
	public Optional<PaymentDetails> fetchPaymentDetailsByID(long paymentId);

	// update payment after booking
	public PaymentDetails updatePaymentAfterBooking(String modeOfPayment, long bookingId);

}