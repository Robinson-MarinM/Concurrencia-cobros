package com.iud.supermercado.service;

import com.iud.supermercado.model.Producto;
import com.iud.supermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoByCode(String code) {
        return productoRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el codigo: " + code));
    }

    public Producto updateProducto(String code, Producto productoDetails) {
        return productoRepository.findById(code).map(producto -> {
            producto.setCodigo(productoDetails.getCodigo());
            producto.setNombre(productoDetails.getNombre());
            producto.setValor(productoDetails.getValor());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con el codigo: " + code));
    }

    public void deleteProducto(String code) {
        productoRepository.deleteById(code);
    }
}
