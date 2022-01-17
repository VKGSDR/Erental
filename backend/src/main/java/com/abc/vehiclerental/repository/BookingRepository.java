package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.vehiclerental.entity.BookingDetails;

public interface BookingRepository extends JpaRepository<BookingDetails, Long> {

}
