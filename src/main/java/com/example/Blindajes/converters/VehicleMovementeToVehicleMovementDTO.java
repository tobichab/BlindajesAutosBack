package com.example.Blindajes.converters;

import com.example.Blindajes.dto.VehicleMovementDTO;
import com.example.Blindajes.model.VehicleMovement;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleMovementeToVehicleMovementDTO implements Converter<VehicleMovement, VehicleMovementDTO> {
    @Override
    public VehicleMovementDTO convert(VehicleMovement source) {
        VehicleMovementDTO vehicleMovementDTO = new VehicleMovementDTO();
        vehicleMovementDTO.setMovementType(source.getMovementType().name());
        vehicleMovementDTO.setVehicleChasis(source.getVehicle().getChasis());
        vehicleMovementDTO.setId(source.getId());
        vehicleMovementDTO.setDateTime(source.getFormattedDateTime());
        vehicleMovementDTO.setUserName(source.getUser().getUsername());

        return vehicleMovementDTO;
    }
}
