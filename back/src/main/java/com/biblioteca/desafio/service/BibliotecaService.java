package com.biblioteca.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.desafio.entities.Biblioteca;
import com.biblioteca.desafio.entities.Livro;
import com.biblioteca.desafio.repository.BibliotecaRepository;

@Service
public class BibliotecaService {
	
	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	
	
	public List<Livro> recuperaLivrosBiblioteca() {
		List<Biblioteca> findAll = bibliotecaRepository.findAll();
		Set<Livro> estoque = findAll.stream()
				.<Livro>flatMap(b -> b.getLivros().stream().map(l -> l.getLivro()))
				.collect(Collectors.toSet());
		
		return new ArrayList<>(estoque);
	}

}
