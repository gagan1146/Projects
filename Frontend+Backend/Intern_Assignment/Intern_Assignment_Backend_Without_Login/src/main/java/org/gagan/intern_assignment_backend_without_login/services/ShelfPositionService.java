package org.gagan.intern_assignment_backend_without_login.services;


import lombok.extern.slf4j.Slf4j;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithShelfPosition;
import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ShelfPositionService {
    private final Driver driver;
    public ShelfPositionService(Driver driver) {
        this.driver = driver;
    }

    public ResponseEntity<List<ShelfWithShelfPosition>> getAllShelfPositions(UUID deviceId) {
        String query = """
            MATCH (n:Device {deviceId:$deviceId, flag:TRUE})-[:HAS]->(sp:ShelfPositions)
            OPTIONAL MATCH (sp)-[:HAS]->(s:Shelf {flag:TRUE} )
            RETURN sp.shelfPositionId AS shelfPositionId, s.shelfName AS shelfName
            """;

        var result = driver.executableQuery(query)
                .withParameters(Map.of("deviceId", deviceId.toString()))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();

        List<ShelfWithShelfPosition> shelves = result.records().stream()
                .map(r -> new ShelfWithShelfPosition(

                        UUID.fromString(r.get("shelfPositionId").asString()),
                        r.get("shelfName").isNull() ? null : r.get("shelfName").asString()
                ))
                .toList();
        log.info("Shelf Position : {}", deviceId);
        return ResponseEntity.ok(shelves);
    }
}