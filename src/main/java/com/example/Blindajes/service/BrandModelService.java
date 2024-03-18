package com.example.Blindajes.service;

import com.example.Blindajes.dto.BrandModelDTO;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.BrandModel;

import java.util.List;

public interface BrandModelService {
    BrandModel saveBrandModel(BrandModelDTO brandModelDTO) throws ResourceAlreadyExistsException;
    void deleteBrandModelById(Long id) throws ResourceNotFoundException;
    BrandModel updateBrandModel(BrandModelDTO brandModelDTO) throws ResourceNotFoundException;
    List<BrandModel> listBrandModels() throws ResourceNotFoundException;
    BrandModel findBrandModelByName(String name) throws ResourceNotFoundException;

    BrandModel modelSetHidden (Long id) throws ResourceNotFoundException;

    List<BrandModel> listBrandModelsHiddenFalse () throws ResourceNotFoundException;
}
