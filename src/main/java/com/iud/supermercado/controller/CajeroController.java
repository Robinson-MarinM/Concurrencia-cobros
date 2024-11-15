package com.iud.supermercado.controller;

import com.iud.supermercado.model.Cajero;
import com.iud.supermercado.service.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    private CajeroService cajeroService;

    @PostMapping("/")
    public ResponseEntity<Cajero> crearCajero(@RequestBody Cajero cajero) {
        return ResponseEntity.ok(cajeroService.createCajero(cajero));
    }

    @GetMapping("/")
    public ResponseEntity<List<Cajero>> obtenerTodosLosCajeros() {
        return ResponseEntity.ok(cajeroService.getAllCajeros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cajero> obtenerCajeroPorId(@PathVariable int id) {
        return ResponseEntity.ok(cajeroService.getCajeroById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cajero> actualizarCajero(@PathVariable int id, @RequestBody Cajero cajeroDetails) {
        return ResponseEntity.ok(cajeroService.updateCajero(id, cajeroDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCajero(@PathVariable int id) {
        cajeroService.deleteCajero(id);
        return ResponseEntity.noContent().build();
    }

}
