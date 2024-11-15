package com.iud.supermercado.controller;

import com.iud.supermercado.model.Producto;
import com.iud.supermercado.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.createProducto(producto));
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String codigo, @RequestBody Producto productoDetails) {
        return ResponseEntity.ok(productoService.updateProducto(codigo, productoDetails));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String codigo) {
        productoService.deleteProducto(codigo);
        return ResponseEntity.noContent().build();
    }
}
