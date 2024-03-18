package com.example.Blindajes.service;

import com.example.Blindajes.dto.WorkGroupDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.WorkGroup;


import java.util.List;

public interface WorkGroupsService {
    WorkGroup saveWorkGroups(WorkGroupDTO workGroupDTO) throws InvalidArgumentException;
    void deleteWorkGroupsById(Long id) throws ResourceNotFoundException;
    WorkGroup updateWorkGroups(WorkGroup workGroup) throws ResourceNotFoundException;
    List<WorkGroup> findAllWorkGroups() throws ResourceNotFoundException;
    WorkGroup findWorkGroupsByName(String name) throws ResourceNotFoundException;

    WorkGroup wgSetHidden (Long id) throws ResourceNotFoundException;
    List<WorkGroup> listWGHiddenFalse () throws ResourceNotFoundException;
}
