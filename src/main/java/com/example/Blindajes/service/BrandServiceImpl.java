package com.example.Blindajes.service;
import com.example.Blindajes.converters.BrandDTOToBrand;
import com.example.Blindajes.dto.BrandDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Brand;
import com.example.Blindajes.model.BrandModel;
import com.example.Blindajes.repository.BrandModelRepository;
import com.example.Blindajes.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandDTOToBrand brandDTOToBrand;
//    private final BrandModelService brandModelService;
    private final BrandModelRepository brandModelRepository;

    @Override
    public Brand saveBrand(BrandDTO brandDTO) throws ResourceAlreadyExistsException, InvalidArgumentException {
        Brand brand = brandDTOToBrand.convert(brandDTO);
        if (brandRepository.findBrandByName(brandDTO.getName()).isPresent()) {
            log.error("Failed to save brand: Brand already exists");
            throw new ResourceAlreadyExistsException("The provided brand already exists");
        }
        if (brand != null){
            log.info("Brand saved");
            return brandRepository.save(brand);
        }
        log.error("The information provided for saving the brand is invalid");
       throw new InvalidArgumentException("Invalid information provided");
    }

    @Override
    public Brand findBrandById(Long id) throws ResourceNotFoundException {
        return brandRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Failed to find brand: Brand not found");
                    return new ResourceNotFoundException("The brand doesn't exist.");
                });
    }

    @Override
    public void deleteBrandById(Long id) throws ResourceNotFoundException {
        if (!brandRepository.existsById(id)) {
            log.error("Failed to delete brand: Brand not found");
            throw new ResourceNotFoundException("There is no brand with the provided id");
        }
        log.info("Brand deleted successfully");
        brandRepository.deleteById(id);
    }

    @Override
    public List<Brand> findAllBrands() throws ResourceNotFoundException {
        List<Brand> brandList = brandRepository.findAll();
        if (brandList.isEmpty()) {
            log.error("Failed to retrieve brands: No brands found");
            throw new ResourceNotFoundException("There are no existing brands");
        }
        return brandList;
    }

    @Override
    public Brand updateBrand(BrandDTO brandDTO) throws ResourceNotFoundException {
        if (!brandRepository.existsById(brandDTO.getId())) {
            log.error("Failed to update brand: Brand not found");
            throw new ResourceNotFoundException("The brand you are trying to update doesn't exist");
        }
        Brand savedBrand = brandRepository.save(brandDTOToBrand.convert(brandDTO));
        log.info("Brand updated successfully");
        return savedBrand;
    }
    @Override
    public Brand findBrandByName(String name) throws ResourceNotFoundException {
        return brandRepository.findBrandByName(name)
                .orElseThrow(() -> {
                    log.error("Failed to find brand: Brand not found");
                    return new ResourceNotFoundException("The brand doesn't exist.");
                });
    }

    @Override
    public Brand brandSetHidden(Long id) throws ResourceNotFoundException {
        Optional<Brand> brandO = brandRepository.findById(id);
        if(brandO.isPresent()){
            Brand brand = brandO.get();
            for (BrandModel brandModel:brand.getBrandModelList()) {
                if (brand.getHidden() == brandModel.getHidden() && !brand.getHidden()){
//                    brandModelService.modelSetHidden(brandModel.getId());
                    brandModel.setHidden(!brandModel.getHidden());
                    brandModelRepository.save(brandModel);
                }
            }
            brand.setHidden(!brand.getHidden());
            brandRepository.save(brand);
            log.info("Hidden field changed");
            return brand;
        }
        log.error("Brand could not be found by name");
        throw new ResourceNotFoundException("Brand not found");
    }

    @Override
    public List<Brand> listBrandsHiddenFalse() throws ResourceNotFoundException {
        List<Brand> brandList = brandRepository.findAllBrandHiddenFalse();
        if(brandList.isEmpty()){
            log.error("There are no unhidden brands");
            throw new ResourceNotFoundException("There are no unhidden brands");
        }
        log.info("All unhidden brands found successfully");
        return brandList;
    }
}