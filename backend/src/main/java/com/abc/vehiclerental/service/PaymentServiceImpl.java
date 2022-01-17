package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.entity.BookingDetails;
import com.abc.vehiclerental.entity.PaymentDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.BookingRepository;
import com.abc.vehiclerental.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public PaymentDetails addPayment(long bookingId, String modeOfPayment) {
		Optional<BookingDetails> bookingDetails = bookingRepository.findById(bookingId);

		PaymentDetails paymentDetails = new PaymentDetails();

		BookingDetails bookingDetails1 = new BookingDetails();
		if (bookingDetails.isEmpty()) {
			throw new ResourceNotFoundException("booking is not existing with id : " + bookingId);
		}
		paymentDetails.setBooking(bookingDetails.get());
		bookingDetails1.setBookingStatus("Confirmed");
		bookingDetails1.setPayment(paymentDetails);
		bookingDetails1.getBookingTimestamp();
		return paymentRepository.save(paymentDetails);
	}

	@Override
	public void updatePayment(PaymentDetails payment) {
		paymentRepository.save(payment);
	}

	@Override
	public void deletePayment(long paymentId) throws ResourceNotFoundException {
		Optional<PaymentDetails> deletePaymentById = paymentRepository.findById(paymentId);
		if (deletePaymentById.isEmpty()) {
			throw new ResourceNotFoundException("cannot delete the payment details with id" + paymentId);
		}
		paymentRepository.deleteById(paymentId);

	}

	@Override
	public List<PaymentDetails> fetchAllPaymentDetails() throws ResourceNotFoundException {

		List<PaymentDetails> paymentDetailsList = paymentRepository.findAll();
		if (paymentDetailsList.isEmpty()) {
			throw new ResourceNotFoundException("cannot fetch the payment details");
		}
		return paymentDetailsList;

	}

	@Override
	public Optional<PaymentDetails> fetchPaymentDetailsByID(long paymentId) throws ResourceNotFoundException {
		Optional<PaymentDetails> paymentDetailsListById = paymentRepository.findById(paymentId);
		if (paymentDetailsListById.isEmpty()) {
			throw new ResourceNotFoundException("cannot fetch the payment details with id" + paymentId);
		}
		return paymentDetailsListById;
	}

	public PaymentDetails updatePaymentAfterBooking(String modeOfPayment, long paymentId) {

		Optional<PaymentDetails> optionalPaymentDetails = paymentRepository.findById(paymentId);
		PaymentDetails paymentDetails = optionalPaymentDetails.get();
		BookingDetails bookingDetails = paymentDetails.getBooking();

		long bookingId1 = bookingDetails.getBookingId();
		long paymentId1 = paymentDetails.getPaymentId();

		if (bookingId1 == paymentId1) {
			bookingDetails.setBookingStatus("Confirmed");
		} else {
			bookingDetails.setBookingStatus("Pending");
		}

		return paymentRepository.save(paymentDetails);

	}
}
