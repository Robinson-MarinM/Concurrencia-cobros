package com.iud.supermercado.repository;

import com.iud.supermercado.model.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajeroRepository extends JpaRepository<Cajero,Integer>{

}
