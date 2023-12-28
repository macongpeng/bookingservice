package com.grandia.controller

import com.grandia.model.Booking
import com.grandia.service.BookingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookings")
class BookingController(private val bookingService: BookingService) {

    @GetMapping
    fun getAllBookings(): ResponseEntity<List<Booking>> = ResponseEntity.ok(bookingService.findAllBookings())

    @GetMapping("/customer/{customerId}")
    fun getBookingsByCustomer(@PathVariable customerId: Long): ResponseEntity<List<Booking>> =
        ResponseEntity.ok(bookingService.findBookingsByCustomerId(customerId))

    @PostMapping
    fun createBooking(@RequestBody booking: Booking): ResponseEntity<Booking> =
        ResponseEntity.ok(bookingService.saveBooking(booking))

    @PutMapping("/{id}")
    fun updateBooking(@PathVariable id: Long, @RequestBody booking: Booking): ResponseEntity<Booking> =
        ResponseEntity.ok(bookingService.saveBooking(booking))

    @DeleteMapping("/{id}")
    fun deleteBooking(@PathVariable id: Long): ResponseEntity<Void> {
        bookingService.deleteBookingById(id)
        return ResponseEntity.ok().build()
    }
}