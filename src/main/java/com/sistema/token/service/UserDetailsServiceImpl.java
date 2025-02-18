package com.sistema.token.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.token.entity.Usuario;
import com.sistema.token.repository.UsuarioRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired 	private UsuarioRepository usuarioRepository;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//permite buscar usuario por el username
		
		Usuario usuario = usuarioRepository.findByUsername(username);//buscar
		if(usuario== null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		return usuario;
	}
	

}
