package org.gagan.routematic_clone.controller;

import org.gagan.routematic_clone.model.Routes;
import org.gagan.routematic_clone.service.RoutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/routes")
public class RoutesController {

    private final RoutesService routesService;

    public RoutesController(RoutesService routesService) {
        this.routesService = routesService;
    }

    @PostMapping
    public ResponseEntity<Routes> createRoute(@RequestBody Routes route) {
        return routesService.createRoute(route);
    }

    @GetMapping
    public ResponseEntity<List<Routes>> getAllRoutes() {
        return routesService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routes> getRouteById(@PathVariable UUID id) {
        return routesService.getRouteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Routes> updateRoute(@PathVariable UUID id,
                                              @RequestBody Routes updatedRoute) {
        return routesService.updateRoute(id, updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID id) {
        return routesService.deleteRoute(id);
    }
}