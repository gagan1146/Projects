package org.gagan.authentication.repository;

import org.gagan.authentication.model.UserModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<UserModel,String> {

    Optional<UserModel> findByEmail(String email);
}
