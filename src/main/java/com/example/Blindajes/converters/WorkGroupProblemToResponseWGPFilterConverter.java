package com.example.Blindajes.converters;

import com.example.Blindajes.dto.ResponseWGPFilter;
import com.example.Blindajes.model.WorkGroupProblem;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class WorkGroupProblemToResponseWGPFilterConverter implements Converter<WorkGroupProblem, ResponseWGPFilter> {
    @Override
    public ResponseWGPFilter convert(WorkGroupProblem source) {
        ResponseWGPFilter responseWGPFilter = new ResponseWGPFilter();
        responseWGPFilter.setId(source.getId());
        responseWGPFilter.setWorkGroupName(source.getWorkGroup().getName());
        responseWGPFilter.setDescription(source.getProblemDescription());
        responseWGPFilter.setVehicleChasis(source.getVehicleQualityControl().getVehicle().getChasis());
        responseWGPFilter.setQualityControlDate(source.getVehicleQualityControl().getFormattedLocalDate());
        return responseWGPFilter;
    }
}
