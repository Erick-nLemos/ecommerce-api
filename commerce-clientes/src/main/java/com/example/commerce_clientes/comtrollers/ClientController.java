package com.example.commerce_clientes.comtrollers;

import com.example.commerce_clientes.dtos.ClientRecordDto;
import com.example.commerce_clientes.models.ClientModel;
import com.example.commerce_clientes.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDto clientRecordDto){
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getAll(){
        List<ClientModel> clientList = clientRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }

    @GetMapping("/clients/{idClient}")
    public ResponseEntity<Object> getOne(@PathVariable("idClient") UUID idClient){
        Optional<ClientModel> clientOpt = clientRepository.findById(idClient);
        if(clientOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientOpt.get());
    }

    @PutMapping("/clients/{idClient}")
    public ResponseEntity<Object> updateClient(@PathVariable("idClient") UUID idClient, @RequestBody @Valid ClientRecordDto clientRecordDto){
        Optional<ClientModel> clientOpt = clientRepository.findById(idClient);
        if(clientOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not Found.");
        }
        ClientModel clientModel = clientOpt.get();
        BeanUtils.copyProperties(clientOpt, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }


}
