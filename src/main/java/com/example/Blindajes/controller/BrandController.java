package com.example.Blindajes.controller;

import com.example.Blindajes.dto.BrandDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Brand;
import com.example.Blindajes.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brand")
//@PreAuthorize("hasRole('ADMIN')") // -> lo dejo a modo de ejemplo
public class BrandController {

    private final BrandService brandService;
//    @PreAuthorize("hasAuthority('admin:create')") // -> lo dejo a modo de ejemplo
    @PostMapping
    public ResponseEntity<Brand> saveBrand(@RequestBody BrandDTO brandDTO) throws ResourceAlreadyExistsException, InvalidArgumentException {
        return ResponseEntity.ok(brandService.saveBrand(brandDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Brand>> findAllBrands() throws ResourceNotFoundException {
        return ResponseEntity.ok(brandService.findAllBrands());
    }

    @GetMapping
    public ResponseEntity<Brand> findBrandById(@RequestParam(value = "id") Long id ) throws ResourceNotFoundException{
        return ResponseEntity.ok(brandService.findBrandById(id));
    }

    @GetMapping("/visible")
    ResponseEntity<List<Brand>> listBrandsHiddenFalse () throws ResourceNotFoundException {
        return ResponseEntity.ok(brandService.listBrandsHiddenFalse());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBrandById(@RequestParam (value = "id") Long id) throws ResourceNotFoundException{
        brandService.deleteBrandById(id);
        return ResponseEntity.ok("Brand deleted successfully");
    }

    @PutMapping("/hide")
    ResponseEntity<Brand> setHiddenBrand(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(brandService.brandSetHidden(id));
    }

    @PutMapping
    public ResponseEntity<Brand> updateBrand(@RequestBody BrandDTO brandDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(brandService.updateBrand(brandDTO));
    }



}
