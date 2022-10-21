package com.crud.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.demo.controlador.dto.UsuarioRegistro;
import com.crud.demo.servicio.RUsuarioService;

@Controller
@RequestMapping("/registro")
public class UsuarioController {
	private RUsuarioService service;

	public UsuarioController(RUsuarioService service) {
		super();
		this.service = service;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistro returnNewUserRegistro() {
		return new UsuarioRegistro();
	}
	
	@GetMapping
    public String showForm() {
        return "registro";
    }
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistro user) {
        service.guardar(user);
        return "redirect:/registro?exito";
    }
}
