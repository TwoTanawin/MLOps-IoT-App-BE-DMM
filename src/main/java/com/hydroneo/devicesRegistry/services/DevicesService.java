package com.hydroneo.devicesRegistry.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hydroneo.devicesRegistry.models.Devices;
import com.hydroneo.devicesRegistry.repository.DevicesRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DevicesService {
    private final DevicesRepository devicesRepository;

    public DevicesService(final DevicesRepository devicesRepository) {
        this.devicesRepository = devicesRepository;
    }

    public Devices createDeviceWithGeneratedSerial() {
        String serial = generateSerialNumber();
        Devices device = Devices.builder()
                .serialNumber(serial)
                .active(false)
                .build();
        return devicesRepository.save(device);
    }

    public List<Devices> getAllDevices() {
        return devicesRepository.findAll();
    }

    public Devices getDevice(Long deviceId) {
        return devicesRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + deviceId));
    }

    public Page<Devices> getDevicesbyPagination(Pageable pageable) {
        return devicesRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Devices updateActive(String serialNumber, boolean status) {
        Devices device = devicesRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + serialNumber));

        if (device.isActive() == status) {
            throw new IllegalStateException("Device is already " + (status ? "active" : "inactive"));
        }

        device.setActive(status);
        return devicesRepository.save(device);
    }

    public Devices deleteDevice(Long deviceId) {
        Devices device = devicesRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + deviceId));

        devicesRepository.deleteById(deviceId);
        return device;
    }

    private String generateSerialNumber() {
        return "SN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
