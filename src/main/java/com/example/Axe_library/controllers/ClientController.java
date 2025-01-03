package com.example.Axe_library.controllers;

import com.example.Axe_library.models.Book;
import com.example.Axe_library.models.Client;
import com.example.Axe_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

@PostMapping
public ResponseEntity<Client> createClient(@RequestBody Client client){
    return ResponseEntity.ok(clientService.registerClient(client));
}

@GetMapping
    public ResponseEntity<List<Client>> getClients(){
    return ResponseEntity.ok(clientService.getClients());
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        try {
            clientService.deleteClientById(id);
            return ResponseEntity.ok("client delete");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/search")
    public List<Client> searchBooks(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String identification){

        if (name != null){
            return clientService.clientByName(name);
        }else if (identification != null){
            return clientService.clientByIdentification(identification);
        }
        return Collections.emptyList();
    }
}
