package com.example.Blindajes.service;

import com.example.Blindajes.dto.AllMonthlyProductivityResponse;
import com.example.Blindajes.dto.MonthlyProductivityResponse;
import com.example.Blindajes.dto.VehicleDTO;
import com.example.Blindajes.dto.VehiclesPerAreaQueryResponse;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Area;
import com.example.Blindajes.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle (VehicleDTO vehicleDTO, String userName) throws ResourceAlreadyExistsException;
    VehicleDTO findVehicleById(String id) throws ResourceNotFoundException;

    void deleteVehicleById(String id) throws ResourceNotFoundException;
    List<Vehicle> findAllVehicles() throws ResourceNotFoundException;

    Vehicle updateVehicle (VehicleDTO vehicleDTO) throws ResourceNotFoundException;

    Vehicle updateVehicleAreaByMovementType (Area area, String chasis) throws ResourceNotFoundException;

    Vehicle findVehicleByChasis (String chasis) throws ResourceNotFoundException ;

    List<VehiclesPerAreaQueryResponse> getAmoutOfVehiclesPerArea ();

    MonthlyProductivityResponse monthlyProductivity();
    MonthlyProductivityResponse weeklyProductivity();

    List<AllMonthlyProductivityResponse> allMonthlyProductivity(int year);

    Page<VehicleDTO> getVehiclesByFilter(String clientName, String purchaseOrder, String areaName,
                                         String modelName, String chasis, Boolean finished, Pageable pageable);

}
