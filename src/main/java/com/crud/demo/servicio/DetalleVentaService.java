package com.crud.demo.servicio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.modelo.DetalleVenta;
import com.crud.demo.repositorio.RDetalleVenta;

@Service
@Transactional
public class DetalleVentaService {
	@Autowired
	private RDetalleVenta detalleRepo;
	
	public void save(DetalleVenta dv) {
		detalleRepo.save(dv);
	}
}
