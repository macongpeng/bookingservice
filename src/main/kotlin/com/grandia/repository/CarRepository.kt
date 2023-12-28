package com.grandia.repository

import com.grandia.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    // Define custom queries if needed, for example:
    fun findByLicensePlate(licensePlate: String): Car?
}