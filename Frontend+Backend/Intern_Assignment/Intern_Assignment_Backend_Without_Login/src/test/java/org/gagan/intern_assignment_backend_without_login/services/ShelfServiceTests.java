package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.entity.Shelf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
public class ShelfServiceTests {

    @Autowired
    private ShelfService shelfService;

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
    public void createShelf(Shelf shelf, UUID shelfPositionId, UUID deviceId) {
        ResponseEntity<Shelf> shelves = shelfService.createShelf(shelf, shelfPositionId, deviceId);
        assertNotNull(shelves);
    }

}
