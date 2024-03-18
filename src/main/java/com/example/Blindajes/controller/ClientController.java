package com.example.Blindajes.controller;

import com.example.Blindajes.dto.ClientDTO;
import com.example.Blindajes.exception.InvalidArgumentException;
import com.example.Blindajes.exception.ResourceAlreadyExistsException;
import com.example.Blindajes.exception.ResourceNotFoundException;
import com.example.Blindajes.model.Client;
import com.example.Blindajes.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody ClientDTO clientDTO) throws InvalidArgumentException, ResourceAlreadyExistsException {
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }
    @GetMapping
    public ResponseEntity<Client> findClientById(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(clientService.findClientById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Client>> findAllClients() throws ResourceNotFoundException {
        return ResponseEntity.ok(clientService.findAllClients());
    }
    @GetMapping("/visible")
    ResponseEntity<List<Client>> listClientHiddenFalse () throws ResourceNotFoundException {
        return ResponseEntity.ok(clientService.listClientHiddenFalse());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteClientById (@RequestParam(value = "id") Long id)throws ResourceNotFoundException{
        clientService.deleteClientById(id);
        return ResponseEntity.ok("The client with id " + id + " has been deleted");
    }

    @PutMapping("/hide")
    ResponseEntity<Client> setHiddenClient(@RequestParam(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(clientService.clientSetHidden(id));
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody ClientDTO clientDTO) throws ResourceNotFoundException, InvalidArgumentException {
        return ResponseEntity.ok( clientService.updateClient(clientDTO));
    }


}
