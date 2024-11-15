package com.iud.supermercado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cajero {
    private int id;
    private String nombre;
    private String apellido;
}
