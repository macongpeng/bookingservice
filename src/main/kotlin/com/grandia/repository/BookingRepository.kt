package com.grandia.repository

import com.grandia.model.Booking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface BookingRepository : JpaRepository<Booking, Long> {
    fun findByCustomerId(customerId: Long): List<Booking>
    fun findByDate(date: LocalDateTime): List<Booking>
}