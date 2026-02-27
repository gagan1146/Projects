package org.gagan.practiceneo4jdriver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.gagan.practiceneo4jdriver.service.DeviceService;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @GetMapping("/devices/get")
    public String showAllDevices(){
        return deviceService.getPeople();
    }
    @GetMapping("/devices")
    public String createData(){
        return deviceService.createTwoPerson();
    }
}
