package org.gagan.routematic_clone.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository {
    Optional<User> findByUserName
}
