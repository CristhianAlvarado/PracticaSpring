package com.crud.demo.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fecha")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalle;

    public Venta(int id, Date fecha, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Venta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleVenta> detalle) {
        this.detalle = detalle;
    }
    
    public int DetalleSize() {
    	return getDetalle().size();
    }
    
    public Float getSubTotal() {
    	Float subtotalFloat = 0.0f;
    	for (DetalleVenta detalleVenta : detalle) {
			subtotalFloat += detalleVenta.getPrecio()*detalleVenta.getCantidad();
		}
    	return subtotalFloat;
    }
    
    public Float getTotal() {
    	Float cantigv = getSubTotal()*0.18f;
    	return cantigv+getSubTotal();
    }
}
