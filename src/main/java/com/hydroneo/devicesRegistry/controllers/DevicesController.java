package com.hydroneo.devicesRegistry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.hydroneo.devicesRegistry.models.Devices;
import com.hydroneo.devicesRegistry.services.DevicesService;

@RestController
@RequestMapping("/api/v1/devices")
public class DevicesController {

    private final DevicesService devicesService;

    @Autowired
    public DevicesController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Devices> createDevice() {
        Devices newDevice = devicesService.createDeviceWithGeneratedSerial();
        return ResponseEntity.ok(newDevice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devices> getDeviceById(@PathVariable Long id) {
        Devices device = devicesService.getDevice(id);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/{serialNumber}/status")
    public ResponseEntity<Devices> updateDeviceStatus(
            @PathVariable String serialNumber,
            @RequestParam boolean active
    ) {
        Devices updated = devicesService.updateActive(serialNumber, active);
        return ResponseEntity.ok(updated);
    }      

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable Long id) {
        devicesService.deleteDevice(id);
        return ResponseEntity.ok("Device deleted successfully.");
    }

    @GetMapping("/getDevicesByPagination")
    public ResponseEntity<Page<Devices>> getDevicesbyPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Devices> device = devicesService.getDevicesbyPagination(pageable);

        if(device.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(device);
    }



}
