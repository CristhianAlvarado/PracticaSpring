package com.crud.demo.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.demo.modelo.Rol;
import com.crud.demo.modelo.Usuario;
import com.crud.demo.controlador.dto.UsuarioRegistro;
import com.crud.demo.repositorio.RUsuario;

@Service
public class UsuarioServiceImpl implements RUsuarioService {

	private RUsuario userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passCryp;

	public UsuarioServiceImpl(RUsuario userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(user.getEmail(), user.getPassword(), mapearRoles(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public Usuario guardar(UsuarioRegistro userR) {
		Usuario user = new Usuario(userR.getNombre(), userR.getApellido(), userR.getEmail(), passCryp.encode(userR.getPassword()), Arrays.asList(new Rol("ROLE_ADMIN"))); 
		return userRepo.save(user);
	}

	@Override
	public List<Usuario> listar() {
		return userRepo.findAll();
	}

	@Override
	public Usuario getUsuario(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public Usuario getUsuarioByEmail(String username) {
		return userRepo.findByEmail(username);
	}

}
