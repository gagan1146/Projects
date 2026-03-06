package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.Vehicle;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface VehicleRepository extends Neo4jRepository<Vehicle, UUID> {
}
