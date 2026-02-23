package org.gagan.intern_assignment_backend_without_login.controller;

import org.gagan.intern_assignment_backend_without_login.dto.ShelfWithDeviceAndShelfPosition;
import org.gagan.intern_assignment_backend_without_login.entity.Shelf;
import org.gagan.intern_assignment_backend_without_login.services.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {
    @Autowired
    private ShelfService selfService;
    @PostMapping("/create/{shelfPositionId}/{deviceId}")
    public ResponseEntity<Shelf> createShelf(@RequestBody Shelf shelf,
                                             @PathVariable  UUID shelfPositionId,
                                             @PathVariable  UUID deviceId) {
        return selfService.createShelf(shelf,shelfPositionId,deviceId);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Shelf> updateShelf(@PathVariable String id,Shelf shelf){
        return selfService.updateShelf(UUID.fromString(id),shelf);
    }
    @GetMapping
    public ResponseEntity<List<ShelfWithDeviceAndShelfPosition>> getAllShelf(){
        return selfService.getAllShelf();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelfById(@PathVariable UUID id){
        return selfService.getShelfById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Shelf> deleteShelfById(@PathVariable String id){
        return selfService.deleteShelfById(UUID.fromString(id));
    }
}
