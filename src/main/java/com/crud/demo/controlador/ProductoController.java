package com.crud.demo.controlador;

import com.crud.demo.modelo.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private com.crud.demo.servicio.ProductoService productoService;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto>productos=productoService.listar();
        model.addAttribute("productos", productos);
        return "producto/listar";
    }

    @GetMapping("/new")
    public String agregar(Model model){
        model.addAttribute("producto", new Producto());
        return "producto/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model, RedirectAttributes redirect){
        model.addAttribute("producto", producto);
    	if (result.hasErrors()) {
			return "producto/form";
		}
    	productoService.save(producto);
        redirect.addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/producto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Producto producto = productoService.getProducto(id);
        model.addAttribute("producto", producto);
        return "producto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(Model model, @PathVariable("id") Long id, RedirectAttributes redirect){
        productoService.delete(id);
        redirect.addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        return "redirect:/producto/listar";
    }
}
