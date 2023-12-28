package com.grandia.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    var name: String,

    @Column(nullable = false, unique = true, length = 100)
    var email: String,

    @Column(nullable = true)
    var phoneNumber: String? = null,

    @Column(nullable = false)
    var registeredAt: LocalDateTime = LocalDateTime.now()
)
