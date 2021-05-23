package com.biblioteca.desafio.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.desafio.entities.Livro;
import com.biblioteca.desafio.service.BibliotecaService;

@RestController
@RequestMapping(value="/bibliotecas")
public class BibliotecaResource {
	
	@Autowired
	private BibliotecaService bibliotecaService;
	
	@GetMapping
	public ResponseEntity<List<Livro>> bibliotecaLivros(){		
		List<Livro> livros = bibliotecaService.recuperaLivrosBiblioteca();
		return ResponseEntity.ok(livros);
	}

}
