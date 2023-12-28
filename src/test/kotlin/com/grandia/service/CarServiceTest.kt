package com.grandia.service

import com.grandia.model.Car
import com.grandia.repository.CarRepository
import com.grandia.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.junit.jupiter.api.Assertions.*

class CarServiceTest {

    private val carRepository: CarRepository = Mockito.mock(CarRepository::class.java)
    private val carService = CarService(carRepository)

    @Test
    fun `When saveCar then car is saved`() {
        val car = Car(make = "Toyota", model = "Corolla", licensePlate = "ABC123")
        Mockito.`when`(carRepository.save(Mockito.any())).thenReturn(car)

        val savedCar = carService.saveCar(car)
        Mockito.verify(carRepository).save(car)
        assertEquals(savedCar.licensePlate, car.licensePlate)
    }
}
