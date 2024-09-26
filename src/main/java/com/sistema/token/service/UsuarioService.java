package com.sistema.token.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.sistema.token.entity.Usuario;
import com.sistema.token.entity.UsuarioRol;

public interface UsuarioService {
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
	public Usuario obtenerUsuario(String username);
	public void eliminarUsuario(Long usuarioId);
	/*public abstract UserDetails loadUserByUsername(String username);*/
	
	

}
