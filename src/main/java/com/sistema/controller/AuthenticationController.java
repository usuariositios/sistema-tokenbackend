package com.sistema.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.token.entity.JwtRequest;
import com.sistema.token.entity.JwtResponse;
import com.sistema.token.entity.Usuario;
import com.sistema.token.security.JwtUtils;
import com.sistema.token.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")//permite el intercambio entre peticiones
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Usuario no encontrado");
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void autenticar(String username, String password) throws Exception {
		try {
		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new Exception("Usuario deshabilitado " + e.getMessage());
		} catch (BadCredentialsException e) {			
			e.printStackTrace();
			throw new Exception("Credenciales invalidas " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/actual-usuario")
	public Usuario obtenerUsuarioActual(Principal principal) {
		return (Usuario)this.userDetailsService.loadUserByUsername(principal.getName());
	}
	
	

}
