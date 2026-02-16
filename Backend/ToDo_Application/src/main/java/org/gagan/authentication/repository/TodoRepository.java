package org.gagan.authentication.repository;

import org.gagan.authentication.model.Todo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface TodoRepository extends Neo4jRepository<Todo, String> {
    List<Todo> findByUserEmail(String userEmail);
}