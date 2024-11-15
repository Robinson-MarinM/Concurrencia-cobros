package com.iud.supermercado.service;

import com.iud.supermercado.model.Cliente;
import com.iud.supermercado.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente createClient(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    public Cliente updateClient(int documento, Cliente clienteDetails) {
        return clienteRepository.findById(documento).map(cliente -> {
            cliente.setDocumento(clienteDetails.getDocumento());
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setApellido(clienteDetails.getApellido());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con el documento:" + documento));
    }

    public void deleteClient(int documento) {
        clienteRepository.deleteById(documento);
    }
}
