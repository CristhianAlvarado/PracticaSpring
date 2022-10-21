package com.crud.demo.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crud.demo.modelo.Usuario;
import com.crud.demo.controlador.dto.UsuarioRegistro;

public interface RUsuarioService extends UserDetailsService{
	
	public Usuario guardar(UsuarioRegistro userR);
	
	public List<Usuario> listar();
}
