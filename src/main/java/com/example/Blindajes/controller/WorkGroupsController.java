package com.example.Blindajes.controller;
import com.example.Blindajes.dto.WorkGroupDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.WorkGroup;
import com.example.Blindajes.service.WorkGroupsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/workGroups")
public class WorkGroupsController {

     private final WorkGroupsService workGroupsService;

     @PostMapping
    public ResponseEntity<WorkGroup> saveWorkGroup (@RequestBody WorkGroupDTO workGroupDTO) throws InvalidArgumentException {
         return ResponseEntity.ok(workGroupsService.saveWorkGroups(workGroupDTO));
     }

     @GetMapping("/all")
    public ResponseEntity<List<WorkGroup>> findAllWorkGroups () throws ResourceNotFoundException {
         return ResponseEntity.ok(workGroupsService.findAllWorkGroups());
     }

     @GetMapping
    public ResponseEntity<WorkGroup> findWorkGroupsByName (@RequestParam (value = "name") String name) throws ResourceNotFoundException {
         return ResponseEntity.ok(workGroupsService.findWorkGroupsByName(name));
     }

    @GetMapping("/visible")
    ResponseEntity<List<WorkGroup>> listWGHiddenFalse () throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.listWGHiddenFalse());
    }


    @DeleteMapping
    public ResponseEntity<String> deleteWorkGroupById(@RequestParam(value = "id") Long id) throws ResourceNotFoundException{
         workGroupsService.deleteWorkGroupsById(id);
         return ResponseEntity.ok("Work Group deleted successfully");
    }

    @PutMapping
    public ResponseEntity<WorkGroup> updateWorkGroups (@RequestBody WorkGroup workGroup) throws ResourceNotFoundException {
         return ResponseEntity.ok(workGroupsService.updateWorkGroups(workGroup));
    }

    @PutMapping("/hide")
    ResponseEntity<WorkGroup> setHiddenWG(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(workGroupsService.wgSetHidden(id));
    }


}
