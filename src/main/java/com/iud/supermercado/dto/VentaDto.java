package com.iud.supermercado.dto;

import com.iud.supermercado.model.Cajero;
import com.iud.supermercado.model.Cliente;
import com.iud.supermercado.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDto {
    private Cajero cajero;
    private Cliente cliente;
    private List<Producto> productos = new ArrayList<>();
    private String timeVenta;

}
