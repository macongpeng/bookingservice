package com.grandia.controller

import com.grandia.model.Car
import com.grandia.service.CarService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Assertions.*

class CarControllerTest {

    private val carService: CarService = Mockito.mock(CarService::class.java)
    private val carController = CarController(carService)

    @Test
    fun `When getCarById then return Car`() {
        val car = Car(make = "Toyota", model = "Corolla", licensePlate = "ABC123")
        Mockito.`when`(carService.findCarById(Mockito.anyLong())).thenReturn(java.util.Optional.of(car))

        val response = carController.getCarById(1L)
        assertEquals(response.statusCode, HttpStatus.OK)
        assertNotNull(response.body)
        assertEquals(response.body?.licensePlate, car.licensePlate)
    }
}
