package com.example.Blindajes.service;

import com.example.Blindajes.dto.ClientDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Client;

import java.util.List;

public interface ClientService {
    Client saveClient(ClientDTO clientDTO) throws ResourceAlreadyExistsException, InvalidArgumentException;
    Client updateClient(ClientDTO clientDTO) throws InvalidArgumentException, ResourceNotFoundException;
    void deleteClientById(Long id) throws ResourceNotFoundException;
    Client findClientById(Long id) throws ResourceNotFoundException;
    Client findClientByName(String name) throws ResourceNotFoundException;
    List<Client> findAllClients() throws ResourceNotFoundException;
    Client clientSetHidden (Long id) throws ResourceNotFoundException;
    List<Client> listClientHiddenFalse () throws ResourceNotFoundException;

}
