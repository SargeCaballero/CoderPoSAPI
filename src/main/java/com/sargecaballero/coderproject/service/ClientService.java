package com.sargecaballero.coderproject.service;

import com.sargecaballero.coderproject.repository.ClientRepository;
import com.sargecaballero.coderproject.repository.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .filter(client -> client.getStatus() > 0)
                .toList();
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id)
                .filter(client -> client.getStatus() > 0);
    }

    public List<Client> getClientsByName(String name) {
        return clientRepository.findByName(name)
                .stream()
                .filter(client -> client.getStatus() > 0)
                .toList();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Integer id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setName(updatedClient.getName());
                    existingClient.setLastname(updatedClient.getLastname());
                    existingClient.setStatus(updatedClient.getStatus());
                    return clientRepository.save(existingClient);
                });
    }

    public void deleteClient(Integer id) {
        clientRepository.findById(id)
                .ifPresent(existingClient -> {
                    existingClient.setStatus(0);
                    clientRepository.save(existingClient);
                });
    }
}
