package com.sistema.token.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sistema.token.entity.Usuario;
import com.sistema.token.entity.UsuarioRol;
import com.sistema.token.repository.RolRepository;
import com.sistema.token.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService { //,UserDetailsService
	@Autowired
	private UsuarioRepository usuarioRepository;//inyeccion
	@Autowired
	private RolRepository rolRepository;
	
	
	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if (usuarioLocal!=null) {
			System.out.println(usuarioLocal.getNombre());
			throw new Exception("El usuario existe");
		}else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuarioLocal = usuarioRepository.save(usuario);
		}
		return usuarioLocal;
	}


	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}


	@Override
	public void eliminarUsuario(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}


	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	}*/
	
	
	

}
