package com.biblioteca.desafio.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.desafio.dto.LivroBibliotecaDTO;
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
		return ResponseEntity.ok().body(livros);
	}
	
	@PostMapping("/{idBiblioteca}")
	public ResponseEntity<Livro> salvaNovoLivroBiblioteca(@PathVariable Long idBiblioteca, @RequestBody LivroBibliotecaDTO livroBibliotecaDTO ){
		Livro livro = bibliotecaService.saveNovoLivroBiblioteca(idBiblioteca, livroBibliotecaDTO);
		return ResponseEntity.ok().body(livro);
		
	}
	
	@DeleteMapping("/{idBiblioteca}/livro/{idLivro}")
	public ResponseEntity deletaLivroBiblioteca(@PathVariable Long idBiblioteca, @PathVariable Long idLivro){
		bibliotecaService.deletaLivroBiblioteca(idBiblioteca, idLivro);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("{idBiblioteca}/livro/{idLivro}")
	public ResponseEntity emprestimoLivroBiblioteca(@PathVariable Long idBiblioteca, @PathVariable Long idLivro, @RequestParam String email){
		bibliotecaService.emprestimoLivroBiblioteca(idBiblioteca, idLivro, email);
		return ResponseEntity.noContent().build();
	}

}
