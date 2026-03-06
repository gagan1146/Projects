package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.Driver;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends Neo4jRepository<Driver, UUID> {
    Optional<Driver> findByEmail(String email);
}
