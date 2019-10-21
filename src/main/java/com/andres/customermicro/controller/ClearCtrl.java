package com.andres.customermicro.controller;

import com.andres.customermicro.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("!prod")
@RestController
public class ClearCtrl {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/clear")
    public void clear(){
        clienteRepository.findAll().forEach( cliente -> clienteRepository.deleteById(cliente.getId()));
    }
}
