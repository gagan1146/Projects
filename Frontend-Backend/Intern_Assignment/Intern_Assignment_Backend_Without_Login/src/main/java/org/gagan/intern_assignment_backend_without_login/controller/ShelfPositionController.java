package org.gagan.intern_assignment_backend_without_login.controller;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfPositionsOnly;
import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithShelfPosition;
import org.gagan.intern_assignment_backend_without_login.services.ShelfPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shelfPosition")
public class ShelfPositionController {
    private final ShelfPositionService shelfPositionService;

    public ShelfPositionController(ShelfPositionService shelfPositionService) {
        this.shelfPositionService = shelfPositionService;
    }
    @GetMapping("/{deviceId}")
    public ResponseEntity<List<ShelfWithShelfPosition>> getAllShelfPositions(@PathVariable UUID deviceId){
        return shelfPositionService.getAllShelfPositions(deviceId);
    }

    @GetMapping("/shelfPositionIds")
    public ResponseEntity<List<ShelfPositionsOnly>> getListOfAllShelfPositions() {
        return shelfPositionService.getListOfAllShelfPositions();
    }
}