package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.Routes;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface RoutesRepository extends Neo4jRepository<Routes, UUID> {
}
