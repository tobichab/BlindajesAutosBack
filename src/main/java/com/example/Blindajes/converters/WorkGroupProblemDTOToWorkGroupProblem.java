package com.example.Blindajes.converters;

import com.example.Blindajes.dto.WorkGroupProblemDTO;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.WorkGroupProblem;
import com.example.Blindajes.service.WorkGroupsService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WorkGroupProblemDTOToWorkGroupProblem implements Converter<WorkGroupProblemDTO, WorkGroupProblem> {

    private final WorkGroupsService workGroupsService;

    @Override
    public WorkGroupProblem convert(WorkGroupProblemDTO source) {
        WorkGroupProblem workGroupProblem = new WorkGroupProblem();
        workGroupProblem.setHasProblem(source.getHasProblem());
        workGroupProblem.setProblemDescription(source.getProblemDescription());
        workGroupProblem.setId(source.getId());

        try {
            workGroupProblem.setWorkGroup(workGroupsService.findWorkGroupsByName(source.getWorkGroupName()));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return workGroupProblem;
    }
}
