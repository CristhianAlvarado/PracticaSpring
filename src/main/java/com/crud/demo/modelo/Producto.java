package com.crud.demo.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="producto")
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(unique = true)
    private String nombre;
    
    @Column(name = "precio")
    private float precio;
    
    @Column(name = "stock")
    private int stock;

    public Producto(){

    }

    public Producto(Long id, String nombre, float precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
