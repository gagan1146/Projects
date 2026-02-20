package org.gagan.routematic_clone.repository;

import org.gagan.routematic_clone.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Neo4jRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
