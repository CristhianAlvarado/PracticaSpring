package com.crud.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo.modelo.Producto;

public interface RProducto extends JpaRepository<Producto, Long>{

}
