package com.sargecaballero.coderproject.controller;

import com.sargecaballero.coderproject.repository.entity.Client;
import com.sargecaballero.coderproject.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base.api.path}/clients")
public class ClientController {

    private final ClientService clientService;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        logger.warn("Entra al método GET (Regresa todos los objetos No borrados");
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        logger.warn("Entra al método GET/id recibe id: {}", id);
        return clientService.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> getClientsByName(@RequestParam String name) {
        logger.warn("Entra al método GET/search?name (Regresa todos los objetos No borrados con el nombre: {}", name);
        List<Client> clients = clientService.getClientsByName(name);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        logger.warn("Entra al método POST recibe: {}", client);
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client updatedClient) {
        logger.warn("Entra al método PUT recibe: {}", updatedClient);
        return clientService.updateClient(id, updatedClient)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        logger.warn("Entra al método DELETE/id recibe id: {}", id);
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
