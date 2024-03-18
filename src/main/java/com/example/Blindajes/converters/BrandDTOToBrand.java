package com.example.Blindajes.converters;


import com.example.Blindajes.dto.BrandDTO;
import com.example.Blindajes.model.Brand;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BrandDTOToBrand implements Converter<BrandDTO, Brand> {
    @Override
    public Brand convert(BrandDTO source) {
        Brand brand = new Brand();
        brand.setId(source.getId());
        brand.setName(source.getName());
        return brand;
    }
}
