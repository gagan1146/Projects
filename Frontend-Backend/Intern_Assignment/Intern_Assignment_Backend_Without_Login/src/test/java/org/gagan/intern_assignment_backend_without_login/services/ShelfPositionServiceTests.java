package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithShelfPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neo4j.driver.Driver;
import org.neo4j.driver.EagerResult;
import org.neo4j.driver.ExecutableQuery;
import org.neo4j.driver.QueryConfig;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Shelf Position Service Tests Here..")
class ShelfPositionServiceTests {

    @Mock
    private Driver driver;

    @Mock
    private ExecutableQuery executableQuery;

    @Mock
    private EagerResult eagerResult;

    @InjectMocks
    private ShelfPositionService shelfPositionService;

    private UUID deviceId;

    @BeforeEach
    void setUp() {
        deviceId = UUID.randomUUID();
    }


    @Test
    @DisplayName("Get All Shelf Positions returning List")
    void getAllShelfPositions_shouldReturnList() {
        Record record = mock(Record.class);
        Value shelfPositionValue = mock(Value.class);
        Value shelfNameValue = mock(Value.class);

        when(driver.executableQuery(any(String.class))).thenReturn(executableQuery);
        when(executableQuery.withParameters(anyMap())).thenReturn(executableQuery);
        when(executableQuery.withConfig(any(QueryConfig.class))).thenReturn(executableQuery);
        when(executableQuery.execute()).thenReturn(eagerResult);
        when(eagerResult.records()).thenReturn(List.of(record));

        UUID shelfPositionId = UUID.randomUUID();

        when(record.get("shelfPositionId")).thenReturn(shelfPositionValue);
        when(shelfPositionValue.asString()).thenReturn(shelfPositionId.toString());

        when(record.get("shelfName")).thenReturn(shelfNameValue);
        when(shelfNameValue.isNull()).thenReturn(false);
        when(shelfNameValue.asString()).thenReturn("Shelf-A");

        ResponseEntity<List<ShelfWithShelfPosition>> response =
                shelfPositionService.getAllShelfPositions(deviceId);

        assertNotNull(response);
        assert response.getBody() != null;
        assertEquals(1, response.getBody().size());
        assertEquals(shelfPositionId, response.getBody().getFirst().getShelfPositionId());
        assertEquals("Shelf-A", response.getBody().getFirst().getShelfName());

        verify(driver, times(1)).executableQuery(any(String.class));
    }


    @Test
    @DisplayName("Get All Shelf Positions Returning Empty List...")
    void getAllShelfPositions_shouldReturnEmptyList() {
        when(driver.executableQuery(any(String.class))).thenReturn(executableQuery);
        when(executableQuery.withParameters(anyMap())).thenReturn(executableQuery);
        when(executableQuery.withConfig(any(QueryConfig.class))).thenReturn(executableQuery);
        when(executableQuery.execute()).thenReturn(eagerResult);
        when(eagerResult.records()).thenReturn(List.of());

        ResponseEntity<List<ShelfWithShelfPosition>> response =
                shelfPositionService.getAllShelfPositions(deviceId);

        assertNotNull(response);
        assert response.getBody() != null;
        assertTrue(response.getBody().isEmpty());

        verify(driver, times(1)).executableQuery(any(String.class));
    }
}