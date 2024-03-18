package com.example.Blindajes.service;

import com.example.Blindajes.dto.VehicleMovementDTO;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.VehicleMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleMovementService {
    VehicleMovement saveVehicleMovement (String chasis, String userName) throws Exception;

    VehicleMovementDTO findVehicleMovementById (Long id) throws ResourceNotFoundException;

    List<VehicleMovementDTO> findAllVehicleMovements () throws ResourceNotFoundException;

    void deleteVehicleMovementById (Long id) throws ResourceNotFoundException;

    VehicleMovement updateVehicleMovement (VehicleMovement vehicleMovement) throws ResourceNotFoundException;

    List<VehicleMovementDTO> findVehicleMovementsByChasis (String chasis) throws ResourceNotFoundException;

    Page<VehicleMovementDTO> getMovementsByFilter (String mtName, String vehicleChasis, String startDate, Long userId, String endDate, Pageable pageable);





}
