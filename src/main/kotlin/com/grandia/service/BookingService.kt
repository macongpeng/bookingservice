package com.grandia.service

import com.grandia.model.Booking
import com.grandia.repository.BookingRepository
import org.springframework.stereotype.Service

@Service
class BookingService(private val bookingRepository: BookingRepository) {
    fun findAllBookings(): List<Booking> = bookingRepository.findAll()

    fun findBookingsByCustomerId(customerId: Long): List<Booking> = bookingRepository.findByCustomerId(customerId)

    fun saveBooking(booking: Booking): Booking = bookingRepository.save(booking)

    fun deleteBookingById(id: Long) = bookingRepository.deleteById(id)
}