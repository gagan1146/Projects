package org.gagan.intern_assignment_backend_without_login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Device {
    private UUID deviceId;
    private String deviceName;
    private String partNumber;
    private String buildingName;
    private String deviceType;
    private int numberOfShelfPositions;
    private Boolean flag;
    public Device(){}

    public Device(String deviceName, String partNumber, String buildingName, String deviceType, int numberOfShelfPositions) {
        this.deviceName = deviceName;
        this.partNumber = partNumber;
        this.buildingName = buildingName;
        this.deviceType = deviceType;
        this.numberOfShelfPositions = numberOfShelfPositions;
        this.flag = true;
    }

    public Device(UUID deviceId, String deviceName, String partNumber, String buildingName, String deviceType, int numberOfShelfPositions) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.partNumber = partNumber;
        this.buildingName = buildingName;
        this.deviceType = deviceType;
        this.numberOfShelfPositions = numberOfShelfPositions;
        this.flag = true;
    }
}

