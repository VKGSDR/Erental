package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import com.abc.vehiclerental.entity.BookingDetails;

public interface BookingService {

	// adding booking details
	public BookingDetails addBookingDetails(long userId, long carId);

	// delete booking details by bookId
	public void deleteBooking(long bookingId);

	// fetching all booking details
	public List<BookingDetails> fetchAllBookingDetails();

	// search book details based on id
	public Optional<BookingDetails> searchBookingById(long bookingId);

	// updating booking based on payment id
	public BookingDetails updateBooking(long bookingId, int noOfDays);

}
