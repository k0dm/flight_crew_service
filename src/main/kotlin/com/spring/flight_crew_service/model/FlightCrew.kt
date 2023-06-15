package com.spring.flight_crew_service.model

import jakarta.persistence.*

@Entity
@Table(name = "flight_crew")
data class FlightCrew(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "pilot", nullable = false)
    val pilot: String,

    @Column(name = "navigator", nullable = false)
    val navigator: String,

    @Column(name = "operator", nullable = false)
    val operator: String,

    @Column(name = "stewardesses", nullable = false)
    val stewardesses: List<String>,

    @Column(name = "is_busy", nullable = false)
    var isBusy: Boolean = false
)