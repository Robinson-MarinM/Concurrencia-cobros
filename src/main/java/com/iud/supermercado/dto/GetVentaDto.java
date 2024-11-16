package com.iud.supermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetVentaDto {
    private int idCajero;
    private int documentoCliente;
    private List<String> productos;
}
