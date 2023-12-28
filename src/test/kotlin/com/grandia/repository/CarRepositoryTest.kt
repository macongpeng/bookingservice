package com.grandia.repository

import com.grandia.model.Car
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.junit.jupiter.api.Assertions.*

@DataJpaTest
class CarRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val carRepository: CarRepository
) {

    @Test
    fun `When findByLicensePlate then return Car`() {
        val car = Car(make = "Toyota", model = "Corolla", licensePlate = "ABC123")
        entityManager.persist(car)
        entityManager.flush()

        val found = carRepository.findByLicensePlate(car.licensePlate)
        assertNotNull(found)
        assertEquals(found?.licensePlate, car.licensePlate)
    }
}