package com.grandia.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column

@Entity
data class Car(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 50)
    var make: String,

    @Column(nullable = false, length = 50)
    var model: String,

    @Column(nullable = false, length = 20)
    var licensePlate: String,

    @Column(nullable = true, length = 100)
    var color: String? = null

    // Additional fields and relationships can be added here
)

