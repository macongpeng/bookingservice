package com.grandia.controller

import com.grandia.model.Car
import com.grandia.service.CarService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(private val carService: CarService) {

    @GetMapping
    fun getAllCars(): ResponseEntity<List<Car>> = ResponseEntity.ok(carService.findAllCars())

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): ResponseEntity<Car> =
        carService.findCarById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun createCar(@RequestBody car: Car): ResponseEntity<Car> =
        ResponseEntity.ok(carService.saveCar(car))

    @PutMapping("/{id}")
    fun updateCar(@PathVariable id: Long, @RequestBody car: Car): ResponseEntity<Car> =
        if (carService.findCarById(id).isPresent) {
            ResponseEntity.ok(carService.saveCar(car))
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Long): ResponseEntity<Void> {
        carService.deleteCarById(id)
        return ResponseEntity.ok().build()
    }

    // Additional endpoints can be added as needed
}