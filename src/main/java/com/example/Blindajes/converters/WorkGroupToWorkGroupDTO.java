package com.example.Blindajes.converters;

import com.example.Blindajes.dto.WorkGroupDTO;
import com.example.Blindajes.model.WorkGroup;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WorkGroupToWorkGroupDTO implements Converter<WorkGroup, WorkGroupDTO>{
    @Override
    public WorkGroupDTO convert(WorkGroup source) {
        WorkGroupDTO workGroupDTO = new WorkGroupDTO();
        workGroupDTO.setName(source.getName());
        workGroupDTO.setExamples(source.getExamples());

        return workGroupDTO;
    }
}
