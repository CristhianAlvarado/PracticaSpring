package com.crud.demo.controlador;

import com.crud.demo.modelo.DetalleVenta;
import com.crud.demo.modelo.Producto;
import com.crud.demo.modelo.Usuario;
import com.crud.demo.modelo.Venta;
import com.crud.demo.servicio.DetalleVentaService;
import com.crud.demo.servicio.ProductoService;
import com.crud.demo.servicio.RUsuarioService;
import com.crud.demo.servicio.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService Ventaservice;

    @Autowired
    private RUsuarioService userService;
    
    @Autowired
    private ProductoService proService;
    @Autowired
    private DetalleVentaService dvService;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Venta>ventas = Ventaservice.listar();
        List<Usuario>users = userService.listar();
        model.addAttribute("ventas", ventas);
        model.addAttribute("users", users);        
        return "venta/listar";
    }

    @GetMapping("/new")
    public String Agregar(Model model){
    	List<Producto> productos=proService.listar();
    	model.addAttribute("productos", productos);
    	
    	model.addAttribute("venta", new Venta());
    	model.addAttribute("size", false);
    	
        return "venta/form";
    }
    
    @PostMapping("save")
    public String save(@RequestParam(value = "idCantidad[]") List<String> idCantidad, @RequestParam(value = "idPrecio[]") List<String> idPrecio, @RequestParam(value = "idProducto[]") List<String> idProducto,@RequestParam(value = "fecha") String fecha, RedirectAttributes redirect) throws ParseException {
    	String fechaString = "";
    	fechaString = fecha;
    	Venta venta = new Venta();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = userService.getUsuarioByEmail(auth.getName());
        venta.setUsuario(user);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formato.parse(fechaString);
        venta.setFecha(date);
        Ventaservice.save(venta);
        
       for (int i = 0; i < idProducto.size(); i++) {
    	   DetalleVenta dVenta = new DetalleVenta();
    	   dVenta.setCantidad(Integer.parseInt(idCantidad.get(i)));
    	   dVenta.setPrecio(Float.parseFloat(idPrecio.get(i)));
    	   Producto pro = proService.getProducto(Long.parseLong(idProducto.get(i)));
    	   pro.restarStock(Integer.parseInt(idCantidad.get(i)));
    	   proService.save(pro);
    	   dVenta.setProducto(pro);
    	   dVenta.setVenta(venta);
    	   
    	   dvService.save(dVenta);
    	   
       }
       redirect.addFlashAttribute("mensaje", "Venta Agregada correctamente")
       			.addFlashAttribute("clase", "success");
       return "redirect:/venta/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
    	List<Producto> productos = proService.listar();
    	model.addAttribute("productos", productos);
    	
    	Venta venta = Ventaservice.getVenta(id);
    	Boolean size = venta.DetalleSize()>0;
    	model.addAttribute("venta", venta);
    	model.addAttribute("size", size);
    	return "venta/form";
    }
}
