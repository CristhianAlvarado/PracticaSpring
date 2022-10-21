package com.crud.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.crud.demo.servicio.RUsuarioService;

@Controller
public class LoginController {

	@Autowired
	private RUsuarioService servicioRepo;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping({"/","/index"})
	public String home(Model model) {
		model.addAttribute("usuarios", servicioRepo.listar());
		return "home";
	}
}
