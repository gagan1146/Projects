package org.gagan.intern_assignment_backend_without_login.config;

import org.gagan.intern_assignment_backend_without_login.exception.CustomException;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class Neo4jConfig {

    @Value("${spring.neo4j.uri}")
    private String dbUri;

    @Value("${spring.neo4j.username}")
    private String dbUser;

    @Value("${spring.neo4j.password}")
    private String dbPassword;

    @Bean
    public Driver driver() {
        try {
            Driver driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
            try {
                driver.verifyConnectivity();
                System.out.println("Connection established.");
                return driver;
            } catch (Exception e) {
                throw new CustomException("Check credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new CustomException("Check Syntax",HttpStatus.UNAUTHORIZED);
        }
    }
}