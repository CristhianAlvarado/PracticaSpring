package com.crud.demo.servicio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.demo.modelo.Producto;
import com.crud.demo.repositorio.RProducto;


@Service
@Transactional
public class ProductoService {

	@Autowired
	private RProducto productoRepo;
	
	public List<Producto>listar(){
		return productoRepo.findAll();
	}
	
	public void save(Producto p) {
		productoRepo.save(p);
	}
	
	public Producto getProducto(Long id) {
		return productoRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		productoRepo.deleteById(id);
	}
}
