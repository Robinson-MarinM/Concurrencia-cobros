package com.iud.supermercado.controller;

import com.iud.supermercado.model.Cliente;
import com.iud.supermercado.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createClient(cliente));
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.getAllClients());
    }

    @PutMapping("/{documento}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int documento, @RequestBody Cliente clienteDetails) {
        return ResponseEntity.ok(clienteService.updateClient(documento, clienteDetails));
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int documento) {
        clienteService.deleteClient(documento);
        return ResponseEntity.noContent().build();
    }
}
