package com.example.Blindajes.converters;

import com.example.Blindajes.dto.DestinationDTO;
import com.example.Blindajes.model.Destination;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DestinationDTOToDestination implements Converter<DestinationDTO, Destination> {
    @Override
    public Destination convert(DestinationDTO source) {
        Destination destination = new Destination();
        destination.setId(source.getId());
        destination.setName(source.getName());
        return destination;
    }
}
