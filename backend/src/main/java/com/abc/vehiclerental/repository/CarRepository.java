package com.abc.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.vehiclerental.entity.CarDetails;

public interface CarRepository extends JpaRepository<CarDetails, Long> {

	@Query("Select c from CarDetails c where c.brand = :brandName and c.isBooked = false")
	public List<CarDetails> findByBrand(@Param("brandName") String brand);

	@Query("Select c from CarDetails c where c.carType = :typeName and c.isBooked = false")
	public List<CarDetails> findByCarType(@Param("typeName") String carType);

	@Query("Select c from CarDetails c where c.color = :colorName and c.isBooked = false")
	public List<CarDetails> findByColor(@Param("colorName") String color);

	@Query("Select c from CarDetails c where c.isBooked = false")
	public List<CarDetails> findByIsBooked();

	@Query("Select c from CarDetails c where c.price <= :priceAmount and c.isBooked = false")
	public List<CarDetails> findByPrice(@Param("priceAmount") double price);

}
