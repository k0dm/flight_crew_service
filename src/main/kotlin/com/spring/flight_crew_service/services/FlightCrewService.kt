package com.spring.flight_crew_service.services


import com.spring.flight_crew_service.model.FlightCrew
import com.spring.flight_crew_service.repository.FlightCrewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FlightCrewService {

    @Autowired
    private lateinit var flightCrewRepository: FlightCrewRepository

    fun saveFlightCrew(flightCrew: FlightCrew): ResponseEntity<FlightCrew> {
        return try {
            val savedFlightCrew = flightCrewRepository.save(flightCrew)
            ResponseEntity.ok(savedFlightCrew)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun updateFlightCrew(updatedFlightCrew: FlightCrew, flightCrewId: Long): ResponseEntity<FlightCrew> {
        return try {
            val flightCrew = flightCrewRepository.findById(flightCrewId)
            if (flightCrew.isPresent) {
                val savedFlightCrew = flightCrewRepository.save(updatedFlightCrew.copy(id = flightCrew.get().id))
                ResponseEntity.ok(savedFlightCrew)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun getFlightCrewById(flightCrewId: Long): ResponseEntity<FlightCrew> {
        return try {
            val flightCrew = flightCrewRepository.findById(flightCrewId)
            if (flightCrew.isPresent) {
                ResponseEntity.ok(flightCrew.get())
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun getAllFlightCrews(): ResponseEntity<List<FlightCrew>> {
        return try {
            val flightCrews = flightCrewRepository.findAll()
            ResponseEntity.ok(flightCrews)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun deleteFlightCrewById(flightCrewId: Long): ResponseEntity<String> {
        return try {
            val flightCrew = flightCrewRepository.findById(flightCrewId)
            if (flightCrew.isPresent) {
                if (flightCrew.get().isBusy) {
                    ResponseEntity.status(HttpStatus.CONFLICT).body("This FlightCrew busy now")
                } else {
                    flightCrewRepository.deleteById(flightCrewId)
                    ResponseEntity.ok("Deleted successfully")
                }
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No FlightCrew for this ID")
            }
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can`t delete FlightCrew")
        }

    }

    fun getAvailableFlightCrewId(): ResponseEntity<List<Long>> {
        return try {
            val allFlightCrews = flightCrewRepository.findAll()
            val availableCrews = allFlightCrews.filter { !it.isBusy }
            val availableIds = availableCrews.map { it.id }
            ResponseEntity.ok(availableIds)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
    fun changeStatusFlightCrew(isBusy: Boolean, id: Long): ResponseEntity<String> {
        return try {

            val flightCrew = flightCrewRepository.findById(id)
            if (flightCrew.isPresent) {
                val updatedFlightCrew = flightCrew.get().copy(isBusy = isBusy)
                flightCrewRepository.save(updatedFlightCrew)
                ResponseEntity.ok("FlightCrew isBusy = $isBusy")

            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No FlightCrew for this ID")
            }

        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can`t change FlightCrew")
        }
    }
}