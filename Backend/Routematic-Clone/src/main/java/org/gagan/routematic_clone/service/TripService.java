package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.model.Trip;
import org.gagan.routematic_clone.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public ResponseEntity<Trip> createTrip(Trip trip) {
        tripRepository.save(trip);
        return ResponseEntity.ok(trip);
    }

    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return ResponseEntity.ok(trips);
    }

    public ResponseEntity<Trip> getTripById(UUID id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Trip> updateTrip(UUID id, Trip updatedTrip) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Trip trip = tripOptional.get();
        trip.setTripTime(updatedTrip.getTripTime());
        trip.setStatus(updatedTrip.getStatus());
        tripRepository.save(trip);
        return ResponseEntity.ok(trip);
    }

    public ResponseEntity<Void> deleteTrip(UUID id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tripRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}