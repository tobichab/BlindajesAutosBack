package com.example.Blindajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleMovementDTO {
    private Long id;
    private String movementType;
    private String vehicleChasis;
    private String dateTime;
    private String userName;
}
