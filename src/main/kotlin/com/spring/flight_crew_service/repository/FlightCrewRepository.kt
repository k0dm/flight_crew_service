package com.spring.flight_crew_service.repository

import com.spring.flight_crew_service.model.FlightCrew
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightCrewRepository : JpaRepository<FlightCrew, Long> {
}