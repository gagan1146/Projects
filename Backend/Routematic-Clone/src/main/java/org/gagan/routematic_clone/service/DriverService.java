package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.dto.loginDTO.DriverLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.DriverLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.DriverSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.DriverSignupResponse;
import org.gagan.routematic_clone.dto.driverDTO.DriverUpdateRequest;
import org.gagan.routematic_clone.dto.driverDTO.DriverResponse;
import org.gagan.routematic_clone.model.Driver;
import org.gagan.routematic_clone.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public DriverService(DriverRepository driverRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.driverRepository = driverRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<DriverSignupResponse> signup(DriverSignupRequest driverSignupRequest) {
        Driver driver = mapper.map(driverSignupRequest, Driver.class);
        Optional<Driver> existingDriver = driverRepository.findByEmail(driver.getEmail());
        if (existingDriver.isPresent()) {
            return ResponseEntity.status(409).build();
        }
        driver.setPassword(passwordEncoder.encode(driverSignupRequest.getPassword()));
        driverRepository.save(driver);
        DriverSignupResponse driverSignupResponse = mapper.map(driver, DriverSignupResponse.class);
        return ResponseEntity.ok(driverSignupResponse);
    }

    public ResponseEntity<DriverLoginResponse> login(DriverLoginRequest driverLoginRequest) {
        Optional<Driver> driver = driverRepository.findByEmail(driverLoginRequest.getEmail());
        if (driver.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        if (passwordEncoder.matches(driverLoginRequest.getPassword(), driver.get().getPassword())) {
            DriverLoginResponse driverLoginResponse = mapper.map(driver.get(), DriverLoginResponse.class);
            return ResponseEntity.ok(driverLoginResponse);
        }
        return ResponseEntity.status(401).build();
    }

    public ResponseEntity<DriverResponse> updateDriver(UUID driverId, DriverUpdateRequest updateRequest) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (driverOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Driver driver = driverOptional.get();
        driver.setLicenseNumber(updateRequest.getLicenseNumber());
        driver.setExperienceYears(updateRequest.getExperienceYears());
        driver.setAvailabilityStatus(updateRequest.getAvailabilityStatus());

        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            driver.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }

        driverRepository.save(driver);
        DriverResponse driverResponse = mapper.map(driver, DriverResponse.class);
        return ResponseEntity.ok(driverResponse);
    }

    public ResponseEntity<List<DriverResponse>> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        List<DriverResponse> driverResponses = drivers.stream()
                .map(driver -> mapper.map(driver, DriverResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(driverResponses);
    }

    public ResponseEntity<DriverResponse> getDriverById(UUID driverId) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (driverOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DriverResponse driverResponse = mapper.map(driverOptional.get(), DriverResponse.class);
        return ResponseEntity.ok(driverResponse);
    }

    public ResponseEntity<Void> deleteDriver(UUID driverId) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (driverOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        driverRepository.deleteById(driverId);
        return ResponseEntity.noContent().build();
    }
}