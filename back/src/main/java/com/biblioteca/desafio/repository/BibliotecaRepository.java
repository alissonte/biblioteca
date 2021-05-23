package com.biblioteca.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.desafio.entities.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long>{
	
	Biblioteca findByNome(String nome);
}
