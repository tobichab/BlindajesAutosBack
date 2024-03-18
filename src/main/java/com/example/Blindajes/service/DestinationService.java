package com.example.Blindajes.service;

import com.example.Blindajes.dto.DestinationDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Destination;

import java.util.List;

public interface DestinationService {
    Destination saveDestination(DestinationDTO destinationDTO) throws ResourceAlreadyExistsException, InvalidArgumentException;
    Destination updateDestination(DestinationDTO destinationDTO) throws InvalidArgumentException, ResourceNotFoundException;
    void deleteDestinationById(Long id) throws ResourceNotFoundException;
    Destination findDestinationById(Long id) throws ResourceNotFoundException;
    Destination findDestinationByName(String name) throws ResourceNotFoundException;
    List<Destination> findAllDestinations() throws ResourceNotFoundException;
    Destination destinationSetHidden (Long id) throws ResourceNotFoundException;
    List<Destination> listDestinationHiddenFalse () throws ResourceNotFoundException;
}
