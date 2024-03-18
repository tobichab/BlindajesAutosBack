package com.example.Blindajes.service;
import com.example.Blindajes.dto.BrandDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Brand;

import java.util.List;

public interface BrandService {
    Brand saveBrand(BrandDTO brandDTO) throws ResourceAlreadyExistsException, InvalidArgumentException;
    Brand findBrandById(Long id) throws ResourceNotFoundException;
    void deleteBrandById(Long id) throws ResourceNotFoundException;
    List<Brand> findAllBrands() throws ResourceNotFoundException;
    Brand updateBrand(BrandDTO brandDTO) throws ResourceNotFoundException;
    Brand findBrandByName (String name) throws ResourceNotFoundException;

    Brand brandSetHidden (Long id) throws ResourceNotFoundException;

    List<Brand> listBrandsHiddenFalse () throws ResourceNotFoundException;
}
