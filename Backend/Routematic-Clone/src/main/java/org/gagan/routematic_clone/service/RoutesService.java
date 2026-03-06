package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.model.Routes;
import org.gagan.routematic_clone.repository.RoutesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoutesService {

    private final RoutesRepository routesRepository;

    public RoutesService(RoutesRepository routesRepository) {
        this.routesRepository = routesRepository;
    }

    public ResponseEntity<Routes> createRoute(Routes route) {
        routesRepository.save(route);
        return ResponseEntity.ok(route);
    }

    public ResponseEntity<List<Routes>> getAllRoutes() {
        List<Routes> routes = routesRepository.findAll();
        return ResponseEntity.ok(routes);
    }

    public ResponseEntity<Routes> getRouteById(UUID id) {
        Optional<Routes> route = routesRepository.findById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Routes> updateRoute(UUID id, Routes updatedRoute) {
        Optional<Routes> routeOptional = routesRepository.findById(id);
        if (routeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Routes route = routeOptional.get();
        route.setStartLocation(updatedRoute.getStartLocation());
        route.setEndLocation(updatedRoute.getEndLocation());
        route.setDistance(updatedRoute.getDistance());
        routesRepository.save(route);
        return ResponseEntity.ok(route);
    }

    public ResponseEntity<Void> deleteRoute(UUID id) {
        Optional<Routes> routeOptional = routesRepository.findById(id);
        if (routeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        routesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}