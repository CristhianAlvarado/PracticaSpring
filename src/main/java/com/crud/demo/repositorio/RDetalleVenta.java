package com.crud.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo.modelo.DetalleVenta;

public interface RDetalleVenta extends JpaRepository<DetalleVenta, Integer>{

}
