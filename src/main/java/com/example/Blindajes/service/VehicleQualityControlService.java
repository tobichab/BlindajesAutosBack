package com.example.Blindajes.service;

import com.example.Blindajes.dto.VehicleQualityControlDTO;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.VehicleQualityControl;

import java.util.List;

public interface VehicleQualityControlService {

    VehicleQualityControl saveVehicleQualityControl (VehicleQualityControlDTO vehicleQualityControlDTO);

    void deleteVehicleQualityControlById (Long id) throws ResourceNotFoundException;
    VehicleQualityControl findVehicleQualityControlById (Long id) throws ResourceNotFoundException;
    List<VehicleQualityControl> findAllVehicleQualityControl() throws ResourceNotFoundException;

    VehicleQualityControl updateVehicleQualityControl (VehicleQualityControlDTO vehicleQualityControlDTO) throws ResourceNotFoundException;

    List<VehicleQualityControl> findVehicleQualityControlByVehicleId (String id) throws ResourceNotFoundException;
}
