package com.abc.vehiclerental.controller;

import java.util.List;


import javax.validation.Valid;

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

import com.abc.vehiclerental.entity.CarDetails;
import com.abc.vehiclerental.service.CarService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/cardetails")
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/save")
	public ResponseEntity<String> saveCar(@Valid @RequestBody CarDetails car) {
		carService.addCar(car);
		return new ResponseEntity<>("Saved car information with id: " + car.getCarId(), HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCar(@RequestBody CarDetails car) {
		carService.updateCar(car);
		return new ResponseEntity<>("Updated car information with id: " + car.getCarId(), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable("id") long carId) {
		carService.deleteCar(carId);
		return new ResponseEntity<>("Car deleted Successfully.", HttpStatus.OK);
	}

	@GetMapping("/searchById/{id}")
	public ResponseEntity<CarDetails> searchCarById(@PathVariable("id") long carId) {
		CarDetails car = carService.searchCarById(carId);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping("/searchByBrand/{brand}")
	public ResponseEntity<List<CarDetails>> searchCarByBrand(@PathVariable("brand") String brand) {
		List<CarDetails> carListByBrand = carService.searchCarByBrand(brand);
		return new ResponseEntity<>(carListByBrand, HttpStatus.OK);
	}

	@GetMapping("/searchByType/{type}")
	public ResponseEntity<List<CarDetails>> searchByCarType(@PathVariable("type") String type) {
		List<CarDetails> carListByType = carService.searchCarByType(type);
		return new ResponseEntity<>(carListByType, HttpStatus.OK);
	}

	@GetMapping("/searchByColor/{color}")
	public ResponseEntity<List<CarDetails>> searchCarByColor(@PathVariable("color") String color) {
		List<CarDetails> carListByColor = carService.searchCarByColor(color);
		return new ResponseEntity<>(carListByColor, HttpStatus.OK);
	}

	@GetMapping("/searchByPrice/{price}")
	public ResponseEntity<List<CarDetails>> searchCarsByPrice(@PathVariable("price") double price) {
		List<CarDetails> carListByPrice = carService.searchCarsByPrice(price);
		return new ResponseEntity<>(carListByPrice, HttpStatus.OK);
	}

	@GetMapping("/getAllCarsByAdmin")
	public ResponseEntity<List<CarDetails>> getAllCarsByAdmin() {
		List<CarDetails> carList = carService.getAllCarsByAdmin();
		return new ResponseEntity<>(carList, HttpStatus.OK);
	}

	@GetMapping("/getAllCarsByUsers")
	public ResponseEntity<List<CarDetails>> getAllCarsByUsers() {
		List<CarDetails> carList = carService.getAllCarsByUsers();
		return new ResponseEntity<>(carList, HttpStatus.OK);
	}

}
