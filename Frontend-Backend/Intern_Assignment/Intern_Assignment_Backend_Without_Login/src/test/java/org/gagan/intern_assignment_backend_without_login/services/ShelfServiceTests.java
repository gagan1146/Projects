package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithDeviceAndShelfPosition;
import org.gagan.intern_assignment_backend_without_login.entity.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neo4j.driver.Driver;
import org.neo4j.driver.ExecutableQuery;
import org.neo4j.driver.EagerResult;
import org.neo4j.driver.QueryConfig;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Shelf Service Test Class")
public class ShelfServiceTests {

    @Mock
    private Driver driver;

    @Mock
    private ExecutableQuery executableQuery;

    @Mock
    private EagerResult eagerResult;

    @Mock
    private Record record;

    @Mock
    private Value value;

    @Mock
    private Node node;

    @InjectMocks
    private ShelfService shelfService;

    @BeforeEach
    void setup() {
        lenient().when(driver.executableQuery(anyString())).thenReturn(executableQuery);
        lenient().when(executableQuery.withParameters(any(Map.class))).thenReturn(executableQuery);
        lenient().when(executableQuery.withConfig(any(QueryConfig.class))).thenReturn(executableQuery);
        lenient().when(executableQuery.execute()).thenReturn(eagerResult);
    }

    static Stream<Arguments> shelfProvider() {
        return Stream.of(
                Arguments.of(new Shelf(UUID.randomUUID(), "Shelf1", "PN111", true),
                        UUID.randomUUID(), UUID.randomUUID()),
                Arguments.of(new Shelf(UUID.randomUUID(), "Shelf2", "PN222", true),
                        UUID.randomUUID(), UUID.randomUUID())
        );
    }

    @ParameterizedTest
    @MethodSource("shelfProvider")
    @DisplayName("Create Shelf Test")
    void createShelf(Shelf shelf, UUID shelfPositionId, UUID deviceId) {
        ResponseEntity<Shelf> response = shelfService.createShelf(shelf, shelfPositionId, deviceId);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assert response.getBody() != null;
        assertNotNull(response.getBody().getShelfId());
    }

    @Test
    @DisplayName("Update Shelf Success")
    void updateShelfSuccess() {
        UUID id = UUID.randomUUID();
        Shelf shelf = new Shelf(null, "Updated", "PN999", true);

        when(eagerResult.records()).thenReturn(List.of(record));
        when(record.get("s")).thenReturn(value);
        when(value.isNull()).thenReturn(false);
        when(value.asNode()).thenReturn(node);
        when(node.get("shelfId")).thenReturn(value);
        when(node.get("shelfName")).thenReturn(value);
        when(node.get("partNumber")).thenReturn(value);
        when(node.get("flag")).thenReturn(value);

        when(value.asString()).thenReturn(id.toString(), "Updated", "PN999");
        when(value.asBoolean()).thenReturn(true);

        ResponseEntity<Shelf> response = shelfService.updateShelf(id, shelf);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Update Shelf Not Found")
    void updateShelfNotFound() {
        UUID id = UUID.randomUUID();
        Shelf shelf = new Shelf(null, "Updated", "PN999", true);

        when(eagerResult.records()).thenReturn(Collections.emptyList());

        ResponseEntity<Shelf> response = shelfService.updateShelf(id, shelf);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    @DisplayName("Get Shelf By Id Success")
    void getShelfByIdSuccess() {
        UUID id = UUID.randomUUID();

        when(eagerResult.records()).thenReturn(List.of(record));
        when(record.get("s")).thenReturn(value);
        when(value.asNode()).thenReturn(node);
        when(node.get("shelfId")).thenReturn(value);
        when(node.get("shelfName")).thenReturn(value);
        when(node.get("partNumber")).thenReturn(value);
        when(node.get("flag")).thenReturn(value);

        when(value.asString()).thenReturn(id.toString(), "Shelf1", "PN111");
        when(value.asBoolean()).thenReturn(true);

        ResponseEntity<Shelf> response = shelfService.getShelfById(id);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get Shelf By Id Not Found")
    void getShelfByIdNotFound() {
        when(eagerResult.records()).thenReturn(Collections.emptyList());
        ResponseEntity<Shelf> response = shelfService.getShelfById(UUID.randomUUID());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    @DisplayName("Delete Shelf Success")
    void deleteShelfSuccess() {
        UUID id = UUID.randomUUID();

        when(eagerResult.records()).thenReturn(List.of(record));
        when(record.get("deletedCount")).thenReturn(value);
        when(value.asInt()).thenReturn(1);
        when(record.get("s")).thenReturn(value);
        when(value.isNull()).thenReturn(false);
        when(value.asNode()).thenReturn(node);
        when(node.get("shelfId")).thenReturn(value);
        when(node.get("shelfName")).thenReturn(value);
        when(node.get("partNumber")).thenReturn(value);
        when(node.get("flag")).thenReturn(value);

        when(value.asString()).thenReturn(id.toString(), "Shelf1", "PN111");
        when(value.asBoolean()).thenReturn(false);

        ResponseEntity<Shelf> response = shelfService.deleteShelfById(id);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Delete Shelf Not Found")
    void deleteShelfNotFound() {
        when(eagerResult.records()).thenReturn(Collections.emptyList());
        ResponseEntity<Shelf> response = shelfService.deleteShelfById(UUID.randomUUID());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    @DisplayName("Get All Shelves")
    void getAllShelves() {
        when(eagerResult.records()).thenReturn(List.of(record));

        when(record.get("shelfId")).thenReturn(value);
        when(record.get("shelfName")).thenReturn(value);
        when(record.get("partNumber")).thenReturn(value);
        when(record.get("flag")).thenReturn(value);
        when(record.get("shelfPositionId")).thenReturn(value);
        when(record.get("deviceName")).thenReturn(value);
        when(record.get("deviceId")).thenReturn(value);

        when(value.asString()).thenReturn(
                "shelfId",
                "Shelf1",
                "PN111",
                "positionId",
                "Device1",
                "deviceId"
        );
        when(value.asBoolean()).thenReturn(true);

        ResponseEntity<List<ShelfWithDeviceAndShelfPosition>> response = shelfService.getAllShelf();
        assertEquals(200, response.getStatusCode().value());
        assert response.getBody() != null;
        assertFalse(response.getBody().isEmpty());
    }
}