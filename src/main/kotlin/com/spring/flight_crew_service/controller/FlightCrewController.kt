package com.spring.flight_crew_service.controller

import com.spring.flight_crew_service.model.FlightCrew
import com.spring.flight_crew_service.services.FlightCrewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/flight-crews")
class FlightCrewController() {

    @Autowired
    private lateinit var flightCrewService: FlightCrewService

    @GetMapping
    fun getAllFlightCrews(): ResponseEntity<List<FlightCrew>> {
        return flightCrewService.getAllFlightCrews()
    }

    @GetMapping("/available-crews")
    fun getAvailableFlightCrewId(): ResponseEntity<List<Long>> {
        return flightCrewService.getAvailableFlightCrewId()
    }

    @GetMapping("/{id}")
    fun getFlightCrewById(@PathVariable id: Long): ResponseEntity<FlightCrew> {
        return flightCrewService.getFlightCrewById(id)
    }

    @PostMapping
    fun createFlightCrew(@RequestBody flightCrew: FlightCrew): ResponseEntity<FlightCrew> {
        return flightCrewService.saveFlightCrew(flightCrew)
    }

    @PutMapping("/{id}")
    fun updateFlightCrew(
        @PathVariable id: Long,
        @RequestBody updatedFlightCrew: FlightCrew
    ): ResponseEntity<FlightCrew> {
        return flightCrewService.updateFlightCrew(updatedFlightCrew, id)
    }

    @PatchMapping("/{id}")
    fun changeStatusFlightCrew(
        @PathVariable id: Long,
        @RequestParam isBusy: Boolean
    ): ResponseEntity<String> {
        return flightCrewService.changeStatusFlightCrew(isBusy, id)
    }


    @DeleteMapping("/{id}")
    fun deleteFlightCrew(@PathVariable id: Long): ResponseEntity<String> {
        return flightCrewService.deleteFlightCrewById(id)
    }
}