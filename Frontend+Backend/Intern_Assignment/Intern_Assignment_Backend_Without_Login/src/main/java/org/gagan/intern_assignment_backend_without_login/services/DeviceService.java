package org.gagan.intern_assignment_backend_without_login.services;

import lombok.extern.slf4j.Slf4j;
import org.gagan.intern_assignment_backend_without_login.entity.Device;
import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@Slf4j
@Service
public class DeviceService {

    private final Driver driver;

    public DeviceService(Driver driver) {
        this.driver = driver;
    }

    public ResponseEntity<Device> createDevice(Device device) {
        List<Map<String, Object>> shelves = new ArrayList<>();
        UUID id = randomUUID();
        for (int i = 1; i <= device.getNumberOfShelfPositions(); i++) {
            Map<String, Object> shelfPositions = new HashMap<>();
            shelfPositions.put("shelfPositionId", randomUUID().toString());
            shelfPositions.put("flag", false);
            shelves.add(shelfPositions);
        }

        var result = driver.executableQuery("""
                        CREATE (d:Device {
                            deviceId: $deviceId,
                            deviceName: $deviceName,
                            partNumber: $partNumber,
                            buildingName: $buildingName,
                            deviceType: $deviceType,
                            numberOfShelfPositions: $numberOfShelfPositions,
                            flag: true
                        })
                        WITH d, $shelves AS shelves
                        UNWIND shelves AS shelf
                        CREATE (s:ShelfPositions {shelfPositionId: shelf.shelfPositionId, flag: shelf.flag, deviceId:d.deviceId})
                        CREATE (d)-[:HAS]->(s)
                        RETURN d, collect(s) AS shelves
                        """)
                .withParameters(Map.of(
                        "deviceId", id.toString(),
                        "deviceName", device.getDeviceName(),
                        "partNumber", device.getPartNumber(),
                        "buildingName", device.getBuildingName(),
                        "deviceType", device.getDeviceType(),
                        "numberOfShelfPositions", device.getNumberOfShelfPositions(),
                        "shelves", shelves
                ))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();
        device.setDeviceId(id);
        log.info("Error while creating shelf: {}", device);
        return ResponseEntity.ok(device);
    }

    public ResponseEntity<List<Device>> getAllDevices() {
        var result = driver.executableQuery("""
                        MATCH (d:Device {flag:true}) RETURN d
                        """)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build()).execute();
        var devices = result.records().stream().map(r -> {
            var node = r.get("d").asNode();
            return new Device(UUID.fromString(node.get("deviceId").asString()), node.get("deviceName").asString(), node.get("partNumber").asString(), node.get("buildingName").asString(), node.get("deviceType").asString(), node.get("numberOfShelfPositions").asInt(), true);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(devices);
    }

    public ResponseEntity<Device> getDeviceById(UUID id) {
        var result = driver.executableQuery("""
                        MATCH (d:Device {deviceId: $id}) RETURN d
                        """)
                .withParameters(Map.of("id", id.toString()))
                .withConfig(QueryConfig.builder().withDatabase("neo4j")
                        .build())
                .execute();
        if (result.records().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var node = result.records().getFirst().get("d").asNode();
        Device device = new Device(UUID.fromString(node.get("deviceId").asString()), node.get("deviceName").asString(), node.get("partNumber").asString(), node.get("buildingName").asString(), node.get("deviceType").asString(), node.get("numberOfShelfPositions").asInt());
        return ResponseEntity.ok(device);
    }

    public ResponseEntity<Device> updateDevice(UUID id, Device updatedDevice) {
        var result = driver.executableQuery("""
                        MATCH (d:Device
                        {deviceId: $id})
                        SET d.deviceName = $deviceName, d.partNumber = $partNumber, d.buildingName = $buildingName, d.deviceType = $deviceType, d.numberOfShelfPositions = $numberOfShelfPositions
                        RETURN d
                        """)
                .withParameters(Map.of("id", id.toString(), "deviceName", updatedDevice.getDeviceName(), "partNumber", updatedDevice.getPartNumber(), "buildingName", updatedDevice.getBuildingName(), "deviceType", updatedDevice.getDeviceType(), "numberOfShelfPositions", updatedDevice.getNumberOfShelfPositions()))
                .withConfig(QueryConfig.builder()
                        .withDatabase("neo4j")
                        .build())
                .execute();
        if (result.records().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedDevice.setDeviceId(id);
        return ResponseEntity.ok(updatedDevice);
    }

    public ResponseEntity<Void> deleteDevice(UUID id) {
        var result = driver.executableQuery("""
                        OPTIONAL MATCH (d:Device {deviceId: $id, flag: TRUE})-[r:HAS]->(sp:ShelfPositions)
                        OPTIONAL MATCH (sp)-[r1:HAS]->(s:Shelf)
                        WITH d, sp, s, r1
                        SET d.flag = false, sp.flag = false, s.flag = false
                        DELETE r1
                        RETURN COUNT(d) AS deletedCount
                        """)
                .withParameters(Map.of("id", id.toString()))
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute();

        var deletedCount = result.records().stream()
                .map(r -> r.get("deletedCount").asInt())
                .findFirst()
                .orElse(0);

        if (deletedCount == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}