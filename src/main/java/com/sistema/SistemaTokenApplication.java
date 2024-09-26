package com.sistema;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.token.entity.Rol;
import com.sistema.token.entity.Usuario;
import com.sistema.token.entity.UsuarioRol;
import com.sistema.token.service.UsuarioService;

@SpringBootApplication
public class SistemaTokenApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaTokenApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		/*Usuario usuario = new Usuario();
		usuario.setNombre("Hola");
		usuario.setUsername("hola");
		usuario.setPassword("hola");
		usuario.setEmail("email@gmail.com");
		usuario.setTelefono("123");
		usuario.setPerfil("foto.png");
		Rol rol = new Rol();
		rol.setRolId(1L); //1 DE TIPO LONG
		rol.setNombre("ADMIN");
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);
		usuarioRoles.add(usuarioRol);	
				
		Usuario guardarUsuario = usuarioService.guardarUsuario(usuario,usuarioRoles);
		System.out.println(guardarUsuario.getUsername());*/
		
	}

}
