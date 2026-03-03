package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.entity.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
import org.neo4j.driver.EagerResult;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTests {

    @Mock
    private Driver driver;

    @Mock
    private ExecutableQuery executableQuery;

    @Mock
    private EagerResult eagerResult;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    void setup() {
        lenient().when(driver.executableQuery(anyString())).thenReturn(executableQuery);
        lenient().when(executableQuery.withParameters(any(Map.class))).thenReturn(executableQuery);
        lenient().when(executableQuery.withConfig(any())).thenReturn(executableQuery);
        lenient().when(executableQuery.execute()).thenReturn(eagerResult);
        lenient().when(eagerResult.records()).thenReturn(List.of());
    }

    @Nested
    class CreateDevice {
        static Stream<Device> deviceProvider() {
            return Stream.of(
                    new Device(UUID.randomUUID(), "Router1", "PN123", "BuildingA", "Router", 4),
                    new Device(UUID.randomUUID(), "Switch1", "PN456", "BuildingB", "Switch", 8),
                    new Device(UUID.randomUUID(), "Firewall1", "PN789", "BuildingC", "Firewall", 2)
            );
        }

        @ParameterizedTest
        @MethodSource("deviceProvider")
        void deviceCreatedSuccessfully(Device device) {
            ResponseEntity<Device> response = deviceService.createDevice(device);
            assertNotNull(response);
            assertNotNull(response.getBody());
        }
    }

    @Nested
    class GetListOfAllDevices {
        @Test
        void getListOfAllDevices() {
            ResponseEntity<List<Device>> response = deviceService.getAllDevices();
            assertNotNull(response);
            assertNotNull(response.getBody());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "af81b604-7488-44d8-8228-e9c32c1292ec",
            "00000000-0000-0000-0000-000000000000"
    })
    void testFindDeviceById(String id) {
        ResponseEntity<Device> response = deviceService.getDeviceById(UUID.fromString(id));
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testGetAllDevices() {
        ResponseEntity<List<Device>> response = deviceService.getAllDevices();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "af81b604-7488-44d8-8228-e9c32c1292ec",
            "00000000-0000-0000-0000-000000000000"
    })
    void testDeleteDevice(String id) {
        ResponseEntity<Void> response = deviceService.deleteDevice(UUID.fromString(id));
        assertEquals(404, response.getStatusCode().value());
    }
}