package com.biblioteca.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.desafio.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	Livro findByTitulo(String titulo);
	
}
