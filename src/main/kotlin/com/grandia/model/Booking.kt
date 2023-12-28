package com.grandia.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
data class Booking(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    val car: Car,

    @Column(nullable = false)
    var date: LocalDateTime,

    @Column(nullable = false, length = 200)
    var serviceDescription: String
)
