package org.gagan.intern_assignment_backend.repository;

import org.gagan.intern_assignment_backend.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends Neo4jRepository<User,String> {
}