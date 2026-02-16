package org.gagan.intern_assignment_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Device {
    @GeneratedValue
    private UUID deviceId;
    private String deviceName;
    private String partNumber;
    private String buildingName;
    private String deviceType;
    private int numberOfShelfPositions;
}
