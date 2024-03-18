package com.example.Blindajes.service;

import com.example.Blindajes.dto.ProblemForModelResponse;
import com.example.Blindajes.dto.ResponseWGPFilter;
import com.example.Blindajes.dto.TotalPercentageQueryResponse;
import com.example.Blindajes.dto.WorkGroupProblemQueryResponse;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.WorkGroupProblem;

import java.util.List;

public interface WorkGroupProblemService {

    WorkGroupProblem getWorkGroupProblemById (Long id) throws ResourceNotFoundException;

    List<WorkGroupProblem> findAllWorkGroupProblems () throws ResourceNotFoundException;

    WorkGroupProblem updateWorkGroupProblem (WorkGroupProblem workGroupProblem) throws InvalidArgumentException;

    void deleteWGPById (Long id) throws ResourceNotFoundException;

    TotalPercentageQueryResponse getPercentageOfProblems () throws ResourceNotFoundException;

    List<WorkGroupProblemQueryResponse> calculatePercentageOfProblemsForWorkGroup (int month, int year) throws ResourceNotFoundException;

//    List<WorkGroupProblemQueryResponse> calculatePercentageOfProblemsInsideWorkGroup () throws ResourceNotFoundException;

    Long countWorkGroupProblemsWithProblem (int month, int year) throws ResourceNotFoundException;

    List<ProblemForModelResponse> getProblemForModel (int month, int year);

    List<ResponseWGPFilter> getWorkGroupProblemsByFilter(String chasis, String workGroup,String startDate, String endDate);
}
