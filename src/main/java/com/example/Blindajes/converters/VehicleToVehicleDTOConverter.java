package com.example.Blindajes.converters;

import com.example.Blindajes.dto.VehicleDTO;
import com.example.Blindajes.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleToVehicleDTOConverter implements Converter<Vehicle, VehicleDTO> {
    @Override
    public VehicleDTO convert(Vehicle source) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(source.getId());
        vehicleDTO.setClient(source.getClient().getName());

        // Asignar destination directamente si source.getDestination() no es null
        vehicleDTO.setDestination(source.getDestination() != null ? source.getDestination().getName() : null);

        vehicleDTO.setChasis(source.getChasis());
        vehicleDTO.setObservations(source.getObservations());
        vehicleDTO.setBrandName(source.getBrand().getName());
        vehicleDTO.setBrandModelName(source.getBrandModel().getName());
        vehicleDTO.setFordKey(source.getFordKey());
        vehicleDTO.setPurchaseOrder(source.getPurchaseOrder());
        vehicleDTO.setArea(source.getArea().toString());

        if (source.getQualityControlList().size() > 0) {
            vehicleDTO.setCanBeCheckedOut(source.getQualityControlList().get(source.getQualityControlList().size() - 1).getCanBeCheckedOut());
        } else {
            vehicleDTO.setCanBeCheckedOut(false);
        }

        return vehicleDTO;
    }

}
