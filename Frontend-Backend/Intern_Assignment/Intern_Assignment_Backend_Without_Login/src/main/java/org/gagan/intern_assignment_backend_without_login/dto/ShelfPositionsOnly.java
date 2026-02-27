package org.gagan.intern_assignment_backend_without_login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ShelfPositionsOnly {
    private UUID shelfPositionId;
}
