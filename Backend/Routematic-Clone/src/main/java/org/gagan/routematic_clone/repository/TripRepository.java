package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.Trip;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface TripRepository extends Neo4jRepository<Trip, UUID> {
}
