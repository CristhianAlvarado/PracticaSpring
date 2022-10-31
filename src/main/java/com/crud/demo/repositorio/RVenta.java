package com.crud.demo.repositorio;

import com.crud.demo.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RVenta extends JpaRepository<Venta, Integer> {
}
