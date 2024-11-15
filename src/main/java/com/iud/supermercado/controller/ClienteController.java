package com.iud.supermercado.controller;

import com.iud.supermercado.model.Cliente;
import com.iud.supermercado.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createClient(cliente));
    }
}
