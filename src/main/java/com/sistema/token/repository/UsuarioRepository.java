package com.sistema.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.token.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {//la entidad y la llave primaria JpaRepository tiene metodos findall, paginacion, lista, eliminar etc
	public Usuario findByUsername(String username);//ya existe
	
	

}
