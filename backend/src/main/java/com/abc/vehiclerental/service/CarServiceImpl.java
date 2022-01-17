package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.entity.CarDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.CarRepository;

/**
 * @author jinal, akshata
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	/**
	 * Saves car details given
	 *
	 * @param car details - brand,color,price,carType
	 */
	@Override
	public void addCar(CarDetails car) {
		carRepository.save(car);
	}

	/**
	 * Updates car details based on car id
	 *
	 * @param car details - carId,brand,color,price,carType
	 */
	@Override
	public void updateCar(CarDetails car) {
		Optional<CarDetails> optionalCar = carRepository.findById(car.getCarId());
		if (optionalCar.isEmpty()) {
			throw new ResourceNotFoundException("Car does not exist with Id: " + car.getCarId());
		}
		carRepository.save(car);
	}

	/**
	 * Deletes car details based on car id
	 *
	 * @param carId-auto-generated id assigned to car
	 */
	@Override
	public void deleteCar(long carId) {
		Optional<CarDetails> optionalCar = carRepository.findById(carId);
		if (optionalCar.isEmpty()) {
			throw new ResourceNotFoundException("Car does not exist with Id: " + carId);
		}
		carRepository.deleteById(carId);
	}

	/**
	 * Search car based on car id
	 *
	 * @param carId-auto-generated id assigned to car
	 * @return car-car details
	 */
	@Override
	public CarDetails searchCarById(long carId) {
		Optional<CarDetails> optionalCar = carRepository.findById(carId);
		if (optionalCar.isEmpty()) {
			throw new ResourceNotFoundException("Car not available with id: " + carId);
		}
		CarDetails car = optionalCar.get();
		return car;
	}

	/**
	 * Search cars based on brand
	 *
	 * @param brand-brand of the car
	 * @return car list-list of the cars based on given brand
	 */
	@Override
	public List<CarDetails> searchCarByBrand(String brand) {
		List<CarDetails> brandCarList = carRepository.findByBrand(brand);
		if (brandCarList.isEmpty()) {
			throw new ResourceNotFoundException("Car not available with Brand: " + brand);
		}
		return brandCarList;
	}

	/**
	 * Search cars based on type
	 *
	 * @param type-type of the car
	 * @return car list-list of the cars based on given type
	 */
	@Override
	public List<CarDetails> searchCarByType(String type) {
		List<CarDetails> carList = carRepository.findByCarType(type);
		if (carList.isEmpty()) {
			throw new ResourceNotFoundException(type + " type car not available");
		}
		return carList;
	}

	/**
	 * Search cars based on color
	 *
	 * @param color-color of the car
	 * @return car list-list of the cars based on given color
	 */
	@Override
	public List<CarDetails> searchCarByColor(String color) {
		List<CarDetails> coloredCarList = carRepository.findByColor(color);
		if (coloredCarList.isEmpty()) {
			throw new ResourceNotFoundException(color + " colored car not available");
		}
		return coloredCarList;
	}

	/**
	 * Search cars based on price
	 *
	 * @param price-price of the car
	 * @return car list-list of the cars whose price is less than or equal to given
	 *         price
	 */
	public List<CarDetails> searchCarsByPrice(double price) {
		List<CarDetails> priceBasedCarList = carRepository.findByPrice(price);
		if (priceBasedCarList.isEmpty()) {
			throw new ResourceNotFoundException("Cars Not Available according to provided budget.");
		}
		return priceBasedCarList;
	}

	/**
	 * Finds list of cars for admin
	 *
	 * @return list of cars
	 */
	@Override
	public List<CarDetails> getAllCarsByAdmin() {
		return carRepository.findAll();
	}

	/**
	 * Finds list of unbooked cars for user
	 *
	 * @return list of unbooked cars
	 */
	@Override
	public List<CarDetails> getAllCarsByUsers() {
		List<CarDetails> carList = carRepository.findByIsBooked();
		if (carList.isEmpty()) {
			throw new ResourceNotFoundException("All Cars are booked.");
		}
		return carList;
	}

}
