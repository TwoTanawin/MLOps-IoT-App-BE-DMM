package com.hydroneo.devicesRegistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hydroneo.devicesRegistry.models.Devices;
import java.util.Optional;


@Repository
public interface DevicesRepository extends JpaRepository<Devices, Long> {
    Page<Devices> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Optional<Devices> findBySerialNumber(String serialNumber);
}
