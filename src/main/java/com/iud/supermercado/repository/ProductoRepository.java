package com.iud.supermercado.repository;

import com.iud.supermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductoRepository extends JpaRepository<Producto,String> {

}
