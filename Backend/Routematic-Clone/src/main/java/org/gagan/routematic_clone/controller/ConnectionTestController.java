package org.gagan.routematic_clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionTestController {
    private final Neo4jClient neo4jClient;
    @GetMapping("/db-check")
    public String checkConnection() {
        return neo4jClient.query("RETURN 'Connected to Neo4j' AS result")
                .fetchAs(String.class)
                .one()
                .orElse("Not connected");
    }
}
