package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.entity.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neo4j.driver.Driver;
import org.neo4j.driver.ExecutableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.css.CSSValue;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTests {

    @Mock
    private Driver driver;

    @Mock
    private ExecutableQuery executableQuery;


    @BeforeEach
    void connection() {
        when(driver.executableQuery(anyString())).thenReturn(executableQuery);

        when(executableQuery.withParameters(any(Map.class))).thenReturn(executableQuery);

        when(executableQuery.withConfig(any())).thenReturn(executableQuery);

        when(executableQuery.execute()).thenReturn(null);


    }

    @InjectMocks
    private DeviceService deviceService;

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


    @Test
    public void testAdd() {
        int a = 2;
        assertEquals(4, a + 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"af81b604-7488-44d8-8228-e9c32c1292ec", "00000000-0000-0000-0000-000000000000"})
    public void testFindDeviceById(String id) {
        ResponseEntity<Device> response = deviceService.getDeviceById(
                UUID.fromString("af81b604-7488-44d8-8228-e9c32c1292ec")
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
    @ValueSource(strings = {"af81b604-7488-44d8-8228-e9c32c1292ec", "af81b604-7488-44d8-8228-e9c32c1292ec"})
    public void testDeleteDevice(UUID id) {
        assertNotNull(deviceService.deleteDevice(id));
    }
}