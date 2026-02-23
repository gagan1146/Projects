package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.entity.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.css.CSSValue;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class DeviceServiceTests {

    static Stream<Device> deviceProvider() {
        return Stream.of(
                new Device(UUID.randomUUID(), "Router1", "PN123", "BuildingA", "Router", 4),
                new Device(UUID.randomUUID(), "Switch1", "PN456", "BuildingB", "Switch", 8),
                new Device(UUID.randomUUID(), "Firewall1", "PN789", "BuildingC", "Firewall", 2)
        );
    }
    static Stream<Device> deviceProvider1() {
        return Stream.of(
                new Device(UUID.randomUUID(), "Router1", "PN123", "BuildingA", "Router", 4),
                new Device(UUID.randomUUID(), "Switch1", "PN456", "BuildingB", "Switch", 8),
                new Device(UUID.randomUUID(), "Firewall1", "PN789", "BuildingC", "Firewall", 2)
        );
    }

    @Autowired
    private DeviceService deviceService;

    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"af81b604-7488-44d8-8228-e9c32c1292ec","00000000-0000-0000-0000-000000000000" })
    public void testFindDeviceById(String id) {
        ResponseEntity<Device> response = deviceService.getDeviceById(
                UUID.fromString("af81b604-7488-44d8-8228-e8c32c1292ec")
        );
        Device device = response.getBody();
        assertNull(device);
    }

    @ParameterizedTest
    @MethodSource("deviceProvider")
    public void testCreateDevice(Device device) {
        ResponseEntity<Device> createNode = deviceService.createDevice(device);
        assertNotNull(createNode);
    }

    @Test
    public void testGetAllDevices() {
        ResponseEntity<List<Device>> devices = deviceService.getAllDevices();
        assertNotNull(devices);
    }

//    @ParameterizedTest
//    @MethodSource("deviceProvider1")
//    public void testUpdateDevice(UUID id, Device device){
//        Device device1 = deviceService.updateDevice(id,device).getBody();
//        assertNotNull(device1);
//    }

    @ParameterizedTest
    @ValueSource(strings = {"af81b604-7488-44d8-8228-e9c32c1292ec","00000000-0000-0000-0000-000000000000" })
    public void testDeleteDevice(UUID id) {
        assertNotNull(deviceService.deleteDevice(id));
    }
}