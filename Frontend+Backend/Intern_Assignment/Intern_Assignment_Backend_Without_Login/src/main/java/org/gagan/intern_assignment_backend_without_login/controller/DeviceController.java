package org.gagan.intern_assignment_backend_without_login.controller;

import org.gagan.intern_assignment_backend_without_login.entity.Device;
import org.gagan.intern_assignment_backend_without_login.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/create")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id) {
        return deviceService.getDeviceById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable UUID id, @RequestBody Device updatedDevice) {
        return deviceService.updateDevice(id, updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        return deviceService.deleteDevice(id);
    }
}