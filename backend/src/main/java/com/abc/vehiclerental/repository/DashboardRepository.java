package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.vehiclerental.entity.CarDetails;

public interface DashboardRepository extends JpaRepository<CarDetails, Long> {

	@Query("Select count(*) from CarDetails c where c.isBooked = true")
	public int findByIsBooked();

	@Query("Select count(*) from CarDetails")
	public int findByCar();

}
