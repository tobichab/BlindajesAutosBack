package com.example.Blindajes.controller;

import com.example.Blindajes.dto.ProblemForModelResponse;
import com.example.Blindajes.dto.ResponseWGPFilter;
import com.example.Blindajes.dto.TotalPercentageQueryResponse;
import com.example.Blindajes.dto.WorkGroupProblemQueryResponse;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.WorkGroupProblem;
import com.example.Blindajes.service.WorkGroupProblemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/workGroupsProblem")
public class WorkGroupProblemController {
    private final WorkGroupProblemService workGroupsService;



    @GetMapping("/all")
    public ResponseEntity<List<WorkGroupProblem>> findAllWorkGroupsProblems () throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.findAllWorkGroupProblems());
    }

    @GetMapping
    public ResponseEntity<WorkGroupProblem> findWorkGroupsProblemById (@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.getWorkGroupProblemById(id));
    }

    @GetMapping("/totalPercentage")
    ResponseEntity<TotalPercentageQueryResponse> getPercentage () throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.getPercentageOfProblems());
    }

    @GetMapping("/percentageForWorkGroup")
    ResponseEntity<List<WorkGroupProblemQueryResponse>> getPercentageForWorkGroup (@RequestParam("month") int month, @RequestParam("year") int year) throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.calculatePercentageOfProblemsForWorkGroup(month, year));
    }

    @GetMapping("/countProblemsForModel")
    ResponseEntity<List<ProblemForModelResponse>> countProblemsForModel (@RequestParam("month") int month,@RequestParam("year") int year) {
        return ResponseEntity.ok(workGroupsService.getProblemForModel(month, year));
    }

    @GetMapping("/filters")
    ResponseEntity<List<ResponseWGPFilter>> getWGPByFilters (@RequestParam (value = "chasis", required = false) String chasis,
                                                             @RequestParam (value = "workGroupName", required = false) String workGroupName,
                                                             @RequestParam (value = "startDate", required = false) String startDate,
                                                             @RequestParam (value = "endDate", required = false) String endDate){
        return ResponseEntity.ok(workGroupsService.getWorkGroupProblemsByFilter(chasis, workGroupName, startDate, endDate));
    }

    @GetMapping("/countWorkGroupWithProblem")
    ResponseEntity<Long> countWorkGroupWithProblem (@RequestParam("month") int month, @RequestParam("year") int year) throws ResourceNotFoundException{
        return ResponseEntity.ok(workGroupsService.countWorkGroupProblemsWithProblem(month, year));
    }

    @PutMapping
    public ResponseEntity<WorkGroupProblem> updateWorkGroups (@RequestBody WorkGroupProblem workGroupProblem) throws InvalidArgumentException {
        return ResponseEntity.ok(workGroupsService.updateWorkGroupProblem(workGroupProblem));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteWGPById(@RequestParam(value = "id") Long id) throws ResourceNotFoundException{
        workGroupsService.deleteWGPById(id);
        return ResponseEntity.ok("Work Group deleted successfully");
    }
    //    @GetMapping("/percentageInsideWorkGroup")
//    ResponseEntity<List<WorkGroupProblemQueryResponse>> getPercentageInsideWorkGroup () throws ResourceNotFoundException {

//        return ResponseEntity.ok(workGroupsService.calculatePercentageOfProblemsInsideWorkGroup());
//    }
}
