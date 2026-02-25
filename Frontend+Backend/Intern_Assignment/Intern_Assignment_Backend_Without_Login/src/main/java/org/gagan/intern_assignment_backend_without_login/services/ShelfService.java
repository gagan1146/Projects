package org.gagan.intern_assignment_backend_without_login.services;

import lombok.extern.slf4j.Slf4j;
import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithDeviceAndShelfPosition;
import org.gagan.intern_assignment_backend_without_login.entity.Shelf;
import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@Service
public class ShelfService {
    private final Driver driver;

    public ShelfService(Driver driver) {
        this.driver = driver;
    }

    public ResponseEntity<Shelf> createShelf(Shelf shelf, UUID shelfPositionId, UUID deviceId) {
        UUID id = randomUUID();
        var query = """
                CREATE (s:Shelf { shelfId: $id, shelfName: $shelfName, partNumber: $partNumber, flag:true })
                WITH s
                MATCH (n:Device { deviceId : $deviceId })-[:HAS]->(sp:ShelfPositions { shelfPositionId : $shelfPositionId, flag : FALSE })
                SET sp.flag = TRUE
                CREATE (sp)-[:HAS]->(s)
                RETURN s
                """;
        var result = driver.executableQuery(query)
                .withParameters(Map.of("id",id.toString(),"shelfName", shelf.getShelfName(), "partNumber", shelf.getPartNumber(),
                        "deviceId", deviceId.toString(),
                        "shelfPositionId", shelfPositionId.toString()
                ))
                .withConfig(QueryConfig.builder()
                        .withDatabase("neo4j")
                        .build())
                .execute();
        shelf.setShelfId(id);
        log.info("Shelf created: {}", shelf);

        return ResponseEntity.ok(shelf);
    }

    public ResponseEntity<Shelf> updateShelf(UUID shelfId, Shelf shelf) {
        String query = """
            MATCH (s:Shelf { shelfId: $shelfId})
            SET s.shelfName = $shelfName,
                s.partNumber = $partNumber,
                s.flag = $flag
            RETURN s
            """;

        Map<String, Object> params = new HashMap<>();
        params.put("shelfId", shelfId.toString());
        if (shelf.getShelfName() != null) params.put("shelfName", shelf.getShelfName());
        if (shelf.getPartNumber() != null) params.put("partNumber", shelf.getPartNumber());
        if (shelf.getFlag() != null) params.put("flag", shelf.getFlag());

        var result = driver.executableQuery(query)
                .withParameters(params)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();

        var updated = result.records().stream()
                .map(r -> r.get("s"))
                .filter(v -> !v.isNull())
                .map(v -> {
                    var node = v.asNode();
                    return new Shelf(
                            UUID.fromString(node.get("shelfId").asString()),
                            node.get("shelfName").asString(),
                            node.get("partNumber").asString(),
                            node.get("flag").asBoolean()
                    );
                })
                .findFirst()
                .orElse(null);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    public ResponseEntity<List<ShelfWithDeviceAndShelfPosition>> getAllShelf() {
        String query = """
                MATCH (d:Device)-[:HAS]->(sp:ShelfPositions)
                MATCH (sp)-[:HAS]->(s:Shelf {flag:true})
                RETURN d.deviceName AS deviceName,
                       sp.shelfPositionId AS shelfPositionId,
                       s.shelfId AS shelfId,
                       s.shelfName AS shelfName,
                       s.partNumber AS partNumber,
                       s.flag AS flag,
                       d.deviceId AS deviceId
                """;

        var result = driver.executableQuery(query)
                .withConfig(QueryConfig.builder()
                        .withDatabase("neo4j")
                        .build())
                .execute();

        List<ShelfWithDeviceAndShelfPosition> shelves = result.records().stream()
                .map(r -> new ShelfWithDeviceAndShelfPosition(
                        r.get("shelfId").asString(),
                        r.get("shelfName").asString(),
                        r.get("partNumber").asString(),
                        r.get("flag").asBoolean(),
                        r.get("shelfPositionId").asString(),
                        r.get("deviceName").asString(),
                        r.get("deviceId").asString()
                ))
                .toList();

        return ResponseEntity.ok(shelves);
    }

    public ResponseEntity<Shelf> getShelfById(UUID id) {
        String query = """
                MATCH (s:Shelf { shelfId: $id })
                RETURN s
                """;
        var result = driver.executableQuery(query)
                .withParameters(Map.of("id", id.toString()))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();
        if (result.records().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var node = result.records().getFirst().get("s").asNode();
        Shelf shelf1 = new Shelf(UUID.fromString(node.get("shelfId").asString()), node.get("shelfName").asString(), node.get("partNumber").asString(), node.get("flag").asBoolean());
        return ResponseEntity.ok(shelf1);
    }


    public ResponseEntity<Shelf> deleteShelfById(UUID id) {
        String query = """
            OPTIONAL MATCH (sp:ShelfPositions)-[r1:HAS]->(s:Shelf {shelfId:$id})
            SET sp.flag = FALSE, s.flag = FALSE
            DELETE r1
            RETURN s, COUNT(s) AS deletedCount
            """;

        var result = driver.executableQuery(query)
                .withParameters(Map.of("id", id.toString()))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();
        var record = result.records().stream().findFirst().orElse(null);
        if (record == null || record.get("deletedCount").asInt() == 0 || record.get("s").isNull()) {
            return ResponseEntity.notFound().build();
        }
        var node = record.get("s").asNode();
        var shelf1 = new Shelf(
                UUID.fromString(node.get("shelfId").asString()),
                node.get("shelfName").asString(),
                node.get("partNumber").asString(),
                node.get("flag").asBoolean()
        );
        log.info("Shelf Deleted..." + shelf1);
        return ResponseEntity.ok(shelf1);
    }
}