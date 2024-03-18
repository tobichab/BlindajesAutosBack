package com.example.Blindajes.service;

import com.example.Blindajes.converters.VehicleDTOToVehicleConverter;
import com.example.Blindajes.converters.VehicleToVehicleDTOConverter;
import com.example.Blindajes.dto.AllMonthlyProductivityResponse;
import com.example.Blindajes.dto.MonthlyProductivityResponse;
import com.example.Blindajes.dto.VehicleDTO;
import com.example.Blindajes.dto.VehiclesPerAreaQueryResponse;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Area;
import com.example.Blindajes.model.MovementType;
import com.example.Blindajes.model.Vehicle;
import com.example.Blindajes.model.VehicleMovement;
import com.example.Blindajes.repository.UserRepository;
import com.example.Blindajes.repository.VehicleMovementRepository;
import com.example.Blindajes.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    private final VehicleDTOToVehicleConverter vehicleDTOToVehicleConverter;
    private final VehicleMovementRepository vehicleMovementRepository;
    private final UserRepository userRepository;
    private final VehicleToVehicleDTOConverter vehicleToVehicleDTOConverter;
    @Override
    public Vehicle saveVehicle(VehicleDTO vehicleDTO, String userName) throws ResourceAlreadyExistsException {
       Vehicle vehicle = vehicleDTOToVehicleConverter.convert(vehicleDTO);
       vehicle.setId(generateId(vehicleDTO.getChasis()));

       if(vehicleRepository.findById(vehicleDTO.getChasis()).isEmpty()){
           VehicleMovement vehicleMovement = new VehicleMovement();
           vehicleMovement.setUser(userRepository.findUserByUsername(userName).get());
           vehicleMovement.setVehicle(vehicle);
           vehicleMovement.setMovementType(MovementType.LOGISTIC_CHECKIN);

           vehicleRepository.save(vehicle);
           vehicleMovementRepository.save(vehicleMovement);
           log.info("Vehicle saved successfully");
           return vehicle;
       }
       log.error("Fail to save vehicle: There is a vehicle with the chasis provided");
        throw new ResourceAlreadyExistsException("There is a vehicle with the chasis provided");
    }

    @Override
    public VehicleDTO findVehicleById(String id) throws ResourceNotFoundException {
        if(vehicleRepository.findById(id).isPresent()){
            log.info("Vehicle found by ID");
            return vehicleToVehicleDTOConverter.convert(vehicleRepository.findById(id).get());
        }
        log.error("Fail to find vehicle by chasis: The vehicle could not be found by the provided chasis");
        throw new ResourceNotFoundException("The vehicle could not be found by the provided chasis");
    }

    @Override
    @Transactional
    public void deleteVehicleById(String id) throws ResourceNotFoundException {
        Optional<Vehicle> vehicleO = vehicleRepository.findById(id);
        if(vehicleO.isPresent()){
            vehicleMovementRepository.deleteMovementByVehicleId(id);
            vehicleRepository.deleteVehicleById(id);
            log.info("Vehicle deleted successfully Id: " + id);

        } else {
            log.error("Fail to delete vehicle: There is no vehicle with the provided ID (id) ");
            throw new ResourceNotFoundException("There is no vehicle with the provided ID (id)");
        }
    }

    @Override
    public List<Vehicle> findAllVehicles() throws ResourceNotFoundException {
        if (vehicleRepository.findAll().size() > 0){
            log.info("All vehicles founded successfully");
            return vehicleRepository.findAll();
        }
        log.error("Fail to list all vehicles: There are no vehicle on the database");
        throw new ResourceNotFoundException("There are no vehicle on the database");
    }

    @Override
    public Vehicle updateVehicle(VehicleDTO vehicleDTO) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleDTOToVehicleConverter.convert(vehicleDTO);

        if(vehicleRepository.findById(vehicleDTO.getId()).isPresent() && vehicle != null){
            log.info("Vehicle updated successfully");
            return vehicleRepository.save(vehicle);
        }
        log.error("Fail to update vehicle: The vehicle trying to update doesn't exists. ");
        throw new ResourceNotFoundException("The vehicle trying to update doesn't exists.");
    }

    @Override
    public Vehicle updateVehicleAreaByMovementType(Area area, String chasis) throws ResourceNotFoundException {
        Optional<Vehicle> optionalVehicle = Optional.of(vehicleRepository.findVehicleByChasis(chasis));
        if(optionalVehicle.isPresent()){
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setArea(area);
            log.info("Vehicle area updated sucessfully");
            return vehicleRepository.save(vehicle);
        }
        log.error("Fail to update the vehicle area: The vehicle trying to update doesn't exists. ");
        throw new ResourceNotFoundException("The vehicle trying to update doesn't exists.");
    }

    @Override
    public Vehicle findVehicleByChasis(String chasis) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findVehicleByChasis(chasis);
       if (vehicle != null){
           return vehicle;
       }
       throw new ResourceNotFoundException("There is no vehicle with the provided chasis");
    }

    @Override
    public List<VehiclesPerAreaQueryResponse> getAmoutOfVehiclesPerArea()  {
        List<VehiclesPerAreaQueryResponse> vehiclesPerAreaQueryResponseList = new ArrayList<>();
        VehiclesPerAreaQueryResponse vehiclesPerAreaQueryResponse = new VehiclesPerAreaQueryResponse();
        VehiclesPerAreaQueryResponse vehiclesPerAreaQueryResponse1 = new VehiclesPerAreaQueryResponse();
        VehiclesPerAreaQueryResponse vehiclesPerAreaQueryResponse2 = new VehiclesPerAreaQueryResponse();
        VehiclesPerAreaQueryResponse vehiclesPerAreaQueryResponse3 = new VehiclesPerAreaQueryResponse();
       vehiclesPerAreaQueryResponse.setName("Logistica sin control de calidad");
       vehiclesPerAreaQueryResponse.setVehiculosEnArea(vehicleRepository.countVehiclesInLogisticAreaWithoutQC());
        vehiclesPerAreaQueryResponse1.setName("Produccion con control de calidad");
        vehiclesPerAreaQueryResponse1.setVehiculosEnArea(vehicleRepository.countVehiclesInProductionAreaWithCanBeCheckedOutTrue());
        vehiclesPerAreaQueryResponse2.setName("Logistica con control de calidad");
        vehiclesPerAreaQueryResponse2.setVehiculosEnArea(vehicleRepository.countVehiclesInLogisticAreaWithCanBeCheckedOutTrue());
        vehiclesPerAreaQueryResponse3.setName("Produccion sin control de calidad");
        vehiclesPerAreaQueryResponse3.setVehiculosEnArea(vehicleRepository.countVehiclesInProductionAreaNotReadyToLeave());
        vehiclesPerAreaQueryResponseList.add(vehiclesPerAreaQueryResponse);
        vehiclesPerAreaQueryResponseList.add(vehiclesPerAreaQueryResponse1);
        vehiclesPerAreaQueryResponseList.add(vehiclesPerAreaQueryResponse2);
        vehiclesPerAreaQueryResponseList.add(vehiclesPerAreaQueryResponse3);

        return vehiclesPerAreaQueryResponseList;

    }

    @Override
    public MonthlyProductivityResponse monthlyProductivity() {
        MonthlyProductivityResponse monthlyProductivityResponse = new MonthlyProductivityResponse();

        LocalDate currentDate = LocalDate.now();

        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        LocalDate previousMonthDate = currentDate.minusMonths(1);
        int previousMonth = previousMonthDate.getMonthValue();
        int previousYear = previousMonthDate.getYear();

        monthlyProductivityResponse.setProductividadActual(vehicleRepository.monthlyProductivity(currentMonth, currentYear));
        monthlyProductivityResponse.setProductividadPasada(vehicleRepository.monthlyProductivity(previousMonth, previousYear));
        return monthlyProductivityResponse;
    }

    @Override
    public List<AllMonthlyProductivityResponse> allMonthlyProductivity(int year) {
        List<Object[]> resultadosDesdeBaseDeDatos = vehicleRepository.allMonthlyProductivity(year);

        List<AllMonthlyProductivityResponse> resultados = new ArrayList<>();

        for (Object[] fila : resultadosDesdeBaseDeDatos) {
            int mes = ((Long) fila[0]).intValue();
            long productividad = (long) fila[1];

            AllMonthlyProductivityResponse resultado = new AllMonthlyProductivityResponse(mes, productividad);
            resultados.add(resultado);
        }
        return resultados;
    }

    @Override
    public Page<VehicleDTO> getVehiclesByFilter(String clientName, String purchaseOrder, String areaName, String modelName, String chasis, Boolean finished, Pageable pageable) {
        Page<Vehicle> vehiclePage;
        if (purchaseOrder == null && clientName == null && modelName == null && areaName == null && chasis == null && finished == null) {
            vehiclePage = vehicleRepository.findAll(pageable);
        } else {
            vehiclePage = vehicleRepository.getVehiclesByFilters(purchaseOrder, clientName, areaName, modelName, chasis, finished, pageable);
        }

        return vehiclePage.map(vehicleToVehicleDTOConverter::convert);
    }


    @Override
    public MonthlyProductivityResponse weeklyProductivity() {
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        int currentYear = currentDate.getYear();
        int currentWeekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());

        LocalDate previousWeekDate = currentDate.minusWeeks(1);
        int previousYear = previousWeekDate.getYear();
        int previousWeekOfYear = previousWeekDate.get(weekFields.weekOfWeekBasedYear());

        MonthlyProductivityResponse monthlyProductivityResponse = new MonthlyProductivityResponse();
        monthlyProductivityResponse.setProductividadActual(vehicleRepository.weeklyProductivity(currentYear, currentWeekOfYear));
        monthlyProductivityResponse.setProductividadPasada(vehicleRepository.weeklyProductivity(previousYear, previousWeekOfYear));

        return monthlyProductivityResponse;

    }


    private String generateId(String chasis) {
        String digits = chasis.substring(chasis.length() - 8);
        return digits;
    }

}
