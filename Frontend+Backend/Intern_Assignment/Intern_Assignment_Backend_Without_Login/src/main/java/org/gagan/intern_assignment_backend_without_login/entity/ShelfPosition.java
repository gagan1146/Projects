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
public class ShelfPosition {
    private UUID shelfPositionId;
    private UUID deviceId;
    private Boolean flag;
}