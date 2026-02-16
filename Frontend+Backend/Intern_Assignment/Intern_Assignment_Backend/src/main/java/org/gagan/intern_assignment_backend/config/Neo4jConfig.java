package org.gagan.intern_assignment_backend.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfig {
    @Bean
    public Driver driver(){
        return GraphDatabase.driver("neo4j://127.0.0.1:7687", AuthTokens.basic("neo4j","Gagan@122"));
    }
}
