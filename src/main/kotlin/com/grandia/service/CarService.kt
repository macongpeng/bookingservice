package com.grandia.service

import com.grandia.model.Car
import com.grandia.repository.CarRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarService(private val carRepository: CarRepository) {

    fun findAllCars(): List<Car> = carRepository.findAll()

    fun findCarById(id: Long): Optional<Car> = carRepository.findById(id)

    fun saveCar(car: Car): Car = carRepository.save(car)

    fun deleteCarById(id: Long) = carRepository.deleteById(id)

    // Additional methods can be implemented as needed
}