package com.crud.demo.servicio;

import com.crud.demo.modelo.Venta;
import com.crud.demo.repositorio.RVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VentaService {
    @Autowired
    private RVenta ventaRepo;

    public void save(Venta venta){
        ventaRepo.save(venta);
    }

    public List<Venta> listar(){
        return ventaRepo.findAll();
    }

    public Venta getVenta(int id){
        return ventaRepo.findById(id).get();
    }
}
