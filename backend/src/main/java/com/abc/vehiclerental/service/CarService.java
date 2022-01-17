package com.abc.vehiclerental.service;

import java.util.List;

import com.abc.vehiclerental.entity.CarDetails;

public interface CarService {

	public void addCar(CarDetails car);

	public void updateCar(CarDetails car);

	public void deleteCar(long carId);

	public CarDetails searchCarById(long carId);

	public List<CarDetails> searchCarByBrand(String brand);

	public List<CarDetails> searchCarByType(String type);

	public List<CarDetails> searchCarByColor(String color);

	public List<CarDetails> searchCarsByPrice(double price);

	public List<CarDetails> getAllCarsByAdmin();

	public List<CarDetails> getAllCarsByUsers();

}
