package com.crud.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.demo.modelo.Usuario;

@Repository
public interface RUsuario extends JpaRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);
}
