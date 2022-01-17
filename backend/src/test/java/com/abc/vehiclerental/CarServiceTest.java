package com.abc.vehiclerental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.vehiclerental.entity.CarDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.CarRepository;
import com.abc.vehiclerental.service.CarServiceImpl;

@SpringBootTest
public class CarServiceTest {

	@InjectMocks
	private CarServiceImpl carServiceImpl;

	@Mock
	private CarRepository carRepository;

	@Test
	public void testAddCar() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(100L);
		carDetails.setBrand("Mahindra");
		carDetails.setCarType("SUV");
		carDetails.setColor("Black");
		carDetails.setPrice(8000);
		carDetails.setModifiedBy("Admin2");
		carDetails.setCreatedBy("Admin1");

		carServiceImpl.addCar(carDetails);

		verify(carRepository, times(1)).save(carDetails);

	}

	@Test
	void testUpdateCar() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(100L);
		carDetails.setBrand("honda");
		carDetails.setCarType("SUV");
		carDetails.setColor("Black");
		carDetails.setPrice(8000);
		carDetails.setModifiedBy("Admin1");
		carDetails.setCreatedBy("Admin2");

		Optional<CarDetails> optionalCarDetails = Optional.of(carDetails);

		when(carRepository.findById(100L)).thenReturn(optionalCarDetails);
		carServiceImpl.updateCar(carDetails);

		verify(carRepository, times(1)).save(carDetails);

	}

	@Test
	void testDeleteCar() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(100L);
		carDetails.setBrand("Mahindra");
		carDetails.setCarType("SUV");
		carDetails.setColor("Black");
		carDetails.setPrice(8000);
		carDetails.setModifiedBy("Admin2");
		carDetails.setCreatedBy("Admin1");

		Optional<CarDetails> optionalCarDetails = Optional.of(carDetails);

		when(carRepository.findById(100L)).thenReturn(optionalCarDetails);
		carServiceImpl.deleteCar(100L);

		verify(carRepository, times(1)).deleteById(100L);

	}

	@Test
	void testSearchById() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Honda");
		carDetails.setColor("White");
		carDetails.setCarType("sedan");
		carDetails.setPrice(205023);
		carDetails.setBooked(false);

		Optional<CarDetails> optionalCar = Optional.of(carDetails);

		when(carRepository.findById(1L)).thenReturn(optionalCar);

		CarDetails carDetails1 = carServiceImpl.searchCarById(1L);

		assertEquals(carDetails1.getCarId(), optionalCar.get().getCarId());
		assertEquals(carDetails.getBrand(), optionalCar.get().getBrand());
		assertEquals(carDetails.getColor(), optionalCar.get().getColor());
		assertEquals(carDetails.getCarType(), optionalCar.get().getCarType());
		assertEquals(carDetails.getPrice(), optionalCar.get().getPrice());
		assertEquals(carDetails.isBooked(), optionalCar.get().isBooked());

	}

	@Test
	void testSearchCarByIdThrowingException() {

		when(carRepository.findById(150L)).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.searchCarById(150L));

	}

	@Test
	void testGetAllCarsByAdmin() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Honda");
		carDetails.setColor("White");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Suzuki");
		carDetails1.setColor("Blue");
		carDetails1.setCarType("sedan");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Honda");
		carDetails2.setColor("Grey");
		carDetails2.setCarType("suv");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(true);

		CarDetails carDetails3 = new CarDetails();
		carDetails3.setCarId(4L);
		carDetails3.setBrand("Honda");
		carDetails3.setColor("White");
		carDetails3.setCarType("suv");
		carDetails3.setPrice(7563230);
		carDetails3.setBooked(true);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);
		carList.add(carDetails3);

		when(carRepository.findAll()).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.getAllCarsByAdmin();

		assertEquals(4, carList1.size());

	}

	@Test
	void testGetAllCarsByUsers() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Honda");
		carDetails.setColor("White");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Suzuki");
		carDetails1.setColor("Blue");
		carDetails1.setCarType("sedan");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Honda");
		carDetails2.setColor("Grey");
		carDetails2.setCarType("suv");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(false);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);

		when(carRepository.findByIsBooked()).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.getAllCarsByUsers();

		assertEquals(3, carList1.size());

	}
	
	@Test
	void testSearchCarByBrand() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Honda");
		carDetails.setColor("White");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Honda");
		carDetails1.setColor("Blue");
		carDetails1.setCarType("sedan");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Honda");
		carDetails2.setColor("Grey");
		carDetails2.setCarType("suv");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(false);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);

		when(carRepository.findByBrand("Honda")).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.searchCarByBrand("Honda");

		assertEquals(3, carList1.size());

	}
	
	@Test
	void testSearchCarByBrandThrowingException() {

		when(carRepository.findByBrand("BMW")).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.searchCarByBrand("BMW"));

	}
	
	@Test
	void testSearchCarByType() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Tata");
		carDetails.setColor("White");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Honda");
		carDetails1.setColor("Blue");
		carDetails1.setCarType("sedan");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Audi");
		carDetails2.setColor("Grey");
		carDetails2.setCarType("sedan");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(false);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);

		when(carRepository.findByCarType("sedan")).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.searchCarByType("sedan");

		assertEquals(3, carList1.size());

	}
	
	@Test
	void testSearchCarByTypeThrowingException() {

		when(carRepository.findByCarType("suv")).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.searchCarByType("suv"));

	}
	
	@Test
	void testSearchCarByColor() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Tata");
		carDetails.setColor("white");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Honda");
		carDetails1.setColor("white");
		carDetails1.setCarType("sedan");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Audi");
		carDetails2.setColor("white");
		carDetails2.setCarType("sedan");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(false);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);

		when(carRepository.findByColor("white")).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.searchCarByColor("white");

		assertEquals(3, carList1.size());

	}
	
	@Test
	void testSearchCarByColorThrowingException() {

		when(carRepository.findByColor("grey")).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.searchCarByColor("grey"));

	}
	
	@Test
	void testSearchCarByPrice() {

		CarDetails carDetails = new CarDetails();
		carDetails.setCarId(1L);
		carDetails.setBrand("Tata");
		carDetails.setColor("white");
		carDetails.setCarType("sedan");
		carDetails.setPrice(9925075);
		carDetails.setBooked(false);

		CarDetails carDetails1 = new CarDetails();
		carDetails1.setCarId(2L);
		carDetails1.setBrand("Honda");
		carDetails1.setColor("blue");
		carDetails1.setCarType("suv");
		carDetails1.setPrice(852364.03);
		carDetails1.setBooked(false);

		CarDetails carDetails2 = new CarDetails();
		carDetails2.setCarId(3L);
		carDetails2.setBrand("Audi");
		carDetails2.setColor("red");
		carDetails2.setCarType("sedan");
		carDetails2.setPrice(4992360.23);
		carDetails2.setBooked(false);

		List<CarDetails> carList = new ArrayList<>();
		carList.add(carDetails);
		carList.add(carDetails1);
		carList.add(carDetails2);

		when(carRepository.findByPrice(9999999.0)).thenReturn(carList);

		List<CarDetails> carList1 = carServiceImpl.searchCarsByPrice(9999999.0);

		assertEquals(3, carList1.size());

	}
	
	@Test
	void testSearchCarByPriceThrowingException() {

		when(carRepository.findByPrice(20000.0)).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.searchCarsByPrice(20000.0));

	}

}
