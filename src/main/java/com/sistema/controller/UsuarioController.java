package com.sistema.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.token.entity.Rol;
import com.sistema.token.entity.Usuario;
import com.sistema.token.entity.UsuarioRol;
import com.sistema.token.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")//permitir cors
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/")
	public Usuario guardarUsuario(@RequestBody Usuario usuario)throws Exception 
	{
		usuario.setPerfil("default.png");
		Set<UsuarioRol> roles = new HashSet<>();
		Rol rol = new Rol();
		rol.setRolId(2L);
		rol.setNombre("NORMAL");
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		roles.add(usuarioRol);
		return usuarioService.guardarUsuario(usuario, roles);
	}
	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) {		
		return usuarioService.obtenerUsuario(username);
		
	}
	@DeleteMapping("/{usuarioId}")
	public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
		usuarioService.eliminarUsuario(usuarioId);
	}

}
