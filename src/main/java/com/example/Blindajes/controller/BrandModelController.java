package com.example.Blindajes.controller;

import com.example.Blindajes.dto.BrandModelDTO;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.BrandModel;
import com.example.Blindajes.service.BrandModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/model")
public class BrandModelController {

    private final BrandModelService brandModelService;
    @PostMapping
    public ResponseEntity<BrandModel> saveBrandModel(@RequestBody BrandModelDTO brandModelDTO) throws ResourceAlreadyExistsException {
        return ResponseEntity.ok(brandModelService.saveBrandModel(brandModelDTO));
    }

    @GetMapping
    public ResponseEntity<BrandModel> getBrandModelByName(@RequestParam(value = "name") String name) throws ResourceNotFoundException {
        return ResponseEntity.ok(brandModelService.findBrandModelByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandModel>> listBrandModels() throws ResourceNotFoundException {
        return ResponseEntity.ok(brandModelService.listBrandModels());
    }

    @GetMapping("/visible")
    ResponseEntity<List<BrandModel>> listBrandModelsHiddenFalse () throws ResourceNotFoundException {
        return ResponseEntity.ok(brandModelService.listBrandModelsHiddenFalse());
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteBrandModel(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        brandModelService.deleteBrandModelById(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Model deleted Successfully"));
    }
    @PutMapping("/hide")
    ResponseEntity<BrandModel> setHiddenBrandModel(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(brandModelService.modelSetHidden(id));
    }

    @PutMapping
    public ResponseEntity<BrandModel> updateBrandModel(@RequestBody BrandModelDTO brandModelDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok( brandModelService.updateBrandModel(brandModelDTO));
    }
}
