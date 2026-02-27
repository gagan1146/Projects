package org.gagan.intern_assignment_backend_without_login.services;

import org.gagan.intern_assignment_backend_without_login.entity.Shelf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTests {

    @Mock
    private Driver driver;

    @Mock
    private ExecutableQuery executableQuery;

    @InjectMocks
    private ShelfService shelfService;

    @BeforeEach
    void connection()
    {
        when(driver.executableQuery(anyString())).thenReturn(executableQuery);

        when(executableQuery.withParameters(any(Map.class))).thenReturn(executableQuery);

        when(executableQuery.withConfig(any())).thenReturn(executableQuery);

        when(executableQuery.execute()).thenReturn(null);


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
    public void createShelf(Shelf shelf, UUID shelfPositionId, UUID deviceId) {
        assertNotNull(shelfService.createShelf(shelf,shelfPositionId,deviceId));
    }

}
