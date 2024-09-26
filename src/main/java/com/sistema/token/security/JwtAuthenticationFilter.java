package com.sistema.token.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistema.token.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {//intercepta las petidiones al servidor
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils ;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)//valida el token
			throws ServletException, IOException {
		if(request.getServletPath().equals("/generate-token") || request.getServletPath().equals("/usuarios/")) {
			filterChain.doFilter(request, response);//permite continuar con el filtro JwtAuthenticationEntryPoint
		}else {
			String requestTokenHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;
			if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = this.jwtUtils.extractUsername(jwtToken);//obtener user name del token
				}catch(ExpiredJwtException ex) {
					System.out.println("el token a expiro");
				    ex.printStackTrace();
				}catch(Exception e) 
				{e.printStackTrace();}
			}else {
				System.out.println("Token invalido, debe empezar con bearer");
			}
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
				if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}else {
					System.out.println("No es valido token ");
					
				}
				
				
			}
			filterChain.doFilter(request, response);
		}
		
	}
	
	

}
