package com.iud.supermercado.util;

import com.iud.supermercado.dto.GetVentaDto;
import com.iud.supermercado.dto.VentaDto;
import com.iud.supermercado.model.Producto;
import com.iud.supermercado.model.Cliente;
import com.iud.supermercado.model.Cajero;
import com.iud.supermercado.service.ProductoService;
import com.iud.supermercado.service.ClienteService;
import com.iud.supermercado.service.CajeroService;

public class Venta implements Runnable {

    private final GetVentaDto ventaDto;
    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final CajeroService cajeroService;

    private final VentaDto resultVentaDto;

    public Venta(GetVentaDto ventaDto, ProductoService productoService, ClienteService clienteService, CajeroService cajeroService, VentaDto resultVentaDto) {
        this.ventaDto = ventaDto;
        this.productoService = productoService;
        this.clienteService = clienteService;
        this.cajeroService = cajeroService;
        this.resultVentaDto = resultVentaDto;
    }

    @Override
    public void run() {
        System.out.println("Venta con Runnable iniciada");
        // measure time
        long startTime = System.currentTimeMillis();
        // Task 1: Get all products by product codes
        ventaDto.getProductos().forEach(productoCodigo -> {
            Producto producto = productoService.getProductoByCodigo(productoCodigo);
            resultVentaDto.getProductos().add(producto);
            try {
                Thread.sleep(1000); // Simulating delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Task interrupted", e);
            }
        });

        // Task 2: Get the client by document
        Cliente cliente = clienteService.getClientByDocument(ventaDto.getDocumentoCliente());
        resultVentaDto.setCliente(cliente);
        try {
            Thread.sleep(1000); // Simulating delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }

        // Task 3: Get the cashier (Cajero)
        Cajero cajero = cajeroService.getCajeroById(ventaDto.getIdCajero());
        resultVentaDto.setCajero(cajero);
        try {
            Thread.sleep(1000); // Simulating delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }

        System.out.println("Venta con Runnable finalizada" + resultVentaDto.toString() + "\n" +
                " time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}

