package com.sistema.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.token.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	

}
