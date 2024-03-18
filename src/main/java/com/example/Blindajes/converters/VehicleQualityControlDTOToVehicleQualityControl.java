package com.example.Blindajes.converters;

import com.example.Blindajes.dto.VehicleQualityControlDTO;
import com.example.Blindajes.dto.WorkGroupProblemDTO;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.VehicleQualityControl;
import com.example.Blindajes.model.WorkGroupProblem;
import com.example.Blindajes.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class VehicleQualityControlDTOToVehicleQualityControl implements Converter <VehicleQualityControlDTO, VehicleQualityControl> {

    private final VehicleService vehicleService;
    private final WorkGroupProblemDTOToWorkGroupProblem workGroupProblemDTOToWorkGroupProblem;
    @Override
    public VehicleQualityControl convert(VehicleQualityControlDTO source) {
        VehicleQualityControl vehicleQualityControl = new VehicleQualityControl();

        try {
            vehicleQualityControl.setVehicle(vehicleService.findVehicleByChasis(source.getChasis()));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        vehicleQualityControl.setId(source.getId());

        List<WorkGroupProblem> workGroupProblemList = new ArrayList<>();
        for (WorkGroupProblemDTO workGroupProblemDTO : source.getWorkGroupProblemDTOList()) {
            WorkGroupProblem workGroupProblem = workGroupProblemDTOToWorkGroupProblem.convert(workGroupProblemDTO);
            workGroupProblem.setVehicleQualityControl(vehicleQualityControl);
            workGroupProblemList.add(workGroupProblem);
        }

        vehicleQualityControl.setWorkGroupProblemList(workGroupProblemList);

        return vehicleQualityControl;
    }
}
