package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.entity.BookingDetails;
import com.abc.vehiclerental.entity.CarDetails;
import com.abc.vehiclerental.entity.UserDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.BookingRepository;
import com.abc.vehiclerental.repository.CarRepository;
import com.abc.vehiclerental.repository.UserRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Override
	public BookingDetails addBookingDetails(long userId, long carId) throws ResourceNotFoundException {
		Optional<CarDetails> carDetails = carRepository.findById(carId);
		Optional<UserDetails> userDetails = userRepository.findById(userId);
		if (carDetails.isEmpty()) {
			throw new ResourceNotFoundException("car is not existing with id : " + carId);
		}
		if (userDetails.isEmpty()) {
			throw new ResourceNotFoundException("user is not existing with id : " + userId);
		}
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setUser(userDetails.get());
		bookingDetails.setBookingStatus("Pending");
		bookingDetails.setCar(carDetails.get());
		return bookingRepository.save(bookingDetails);

	}

	@Override
	public BookingDetails updateBooking(long bookingId, int noOfDays) {
		Optional<BookingDetails> optionalBookingDetails = bookingRepository.findById(bookingId);
		BookingDetails bookDetails = optionalBookingDetails.get();
		CarDetails carDetails = bookDetails.getCar();

		double price = carDetails.getPrice();

		bookDetails.setNoOfDays(noOfDays);
		double totalAmount = price * noOfDays;
		bookDetails.setTotalAmount(totalAmount);
		return bookingRepository.save(bookDetails);
	}

	@Override
	public void deleteBooking(long bookingId) throws ResourceNotFoundException {
		Optional<BookingDetails> deleteBookById = bookingRepository.findById(bookingId);
		if (deleteBookById.isEmpty()) {
			throw new ResourceNotFoundException("cannot delete the booking details with id :" + bookingId);
		} else {
			bookingRepository.deleteById(bookingId);
			BookingDetails bookingDetails = new BookingDetails();
			bookingDetails.setBookingStatus("Cancelled");
		}

	}

	@Override
	public List<BookingDetails> fetchAllBookingDetails() throws ResourceNotFoundException {
		List<BookingDetails> book = bookingRepository.findAll();
		if (book.isEmpty()) {
			throw new ResourceNotFoundException("cannot fetch the booking details");
		} else {
			return book;
		}

	}

	@Override
	public Optional<BookingDetails> searchBookingById(long bookingId) throws ResourceNotFoundException {

		Optional<BookingDetails> bookingByIdList = bookingRepository.findById(bookingId);
		if (bookingByIdList.isEmpty()) {
			throw new ResourceNotFoundException("cannot fetch the booking details with id" + bookingId);
		} else {
			return bookingByIdList;
		}

	}

}
