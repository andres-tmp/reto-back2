package com.andres.customermicro.controller;

import com.andres.customermicro.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("!prod")
@RestController
public class ClearCtrl {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/clear")
    public void clear() {
        clienteService.findAll().forEach(cliente -> clienteService.deleteById(cliente.getId()));
    }
}
