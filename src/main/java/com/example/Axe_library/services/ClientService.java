package com.example.Axe_library.services;

import com.example.Axe_library.models.Client;
import com.example.Axe_library.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
   ClientRepository clientRepository;

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client registerClient(Client client){
        return clientRepository.save(client);

    }

    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("cliente with ID " + id + " not found");
        }
        clientRepository.deleteById(id);
    }

    public List<Client> clientByName(String name){
        return clientRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Client> clientByIdentification(String identification){
        return clientRepository.findByIdentificationContainingIgnoreCase(identification);
    }


}

