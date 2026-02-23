package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithShelfPosition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ShelfPositionServiceTests {

    @Autowired
    private ShelfPositionService shelfPositionService;
    @ParameterizedTest
    @ValueSource(strings = {"af81b604-7488-44d8-8228-e9c32c1292ec","00000000-0000-0000-0000-000000000000" })
    public void getAllShelfPositions(String deviceId){
        ResponseEntity<List<ShelfWithShelfPosition>> shelfPositions;
        shelfPositions = shelfPositionService.getAllShelfPositions(UUID.fromString(deviceId));
        assertNotNull(shelfPositions);
    }
}
