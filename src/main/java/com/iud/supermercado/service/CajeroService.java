package com.iud.supermercado.service;

import com.iud.supermercado.model.Cajero;
import com.iud.supermercado.repository.CajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajeroService {

    @Autowired
    private CajeroRepository cajeroRepository;


    public Cajero createCajero(Cajero cajero) {
        return cajeroRepository.save(cajero);
    }

    public List<Cajero> getAllCajeros() {
        return cajeroRepository.findAll();
    }

    public Cajero getCajeroById(int id) {
        return cajeroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cajero no encontrado con el ID: " + id));
    }

    public Cajero updateCajero(int id, Cajero cajeroDetails) {
        return cajeroRepository.findById(id).map(cajero -> {
            cajero.setNombre(cajeroDetails.getNombre());
            cajero.setApellido(cajeroDetails.getApellido());
            cajero.setId(cajeroDetails.getId());
            return cajeroRepository.save(cajero);
        }).orElseThrow(() -> new RuntimeException("Cajero no encontrado con el ID: " + id));
    }

    public void deleteCajero(int id) {
        cajeroRepository.deleteById(id);
    }
}

