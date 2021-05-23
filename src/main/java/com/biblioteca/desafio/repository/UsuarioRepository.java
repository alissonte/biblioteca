package com.biblioteca.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.desafio.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
	
}
