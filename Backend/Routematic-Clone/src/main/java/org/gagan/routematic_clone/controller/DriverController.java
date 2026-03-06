package org.gagan.routematic_clone.controller;

import org.gagan.routematic_clone.dto.loginDTO.DriverLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.DriverLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.DriverSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.DriverSignupResponse;
import org.gagan.routematic_clone.dto.driverDTO.DriverUpdateRequest;
import org.gagan.routematic_clone.dto.driverDTO.DriverResponse;
import org.gagan.routematic_clone.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/signup")
    public ResponseEntity<DriverSignupResponse> signup(@RequestBody DriverSignupRequest request) {
        return driverService.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<DriverLoginResponse> login(@RequestBody DriverLoginRequest request) {
        return driverService.login(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable UUID id,
                                                       @RequestBody DriverUpdateRequest request) {
        return driverService.updateDriver(id, request);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable UUID id) {
        return driverService.getDriverById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID id) {
        return driverService.deleteDriver(id);
    }
}