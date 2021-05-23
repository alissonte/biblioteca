package com.biblioteca.desafio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.desafio.entities.Biblioteca;
import com.biblioteca.desafio.repository.BibliotecaRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_METHOD)
public class IntegrationBiliotecaRepositoryTest {
	
	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	
	
	private Biblioteca biblioteca;
	private String nome;
	
	
	@BeforeEach
	public void setup() {
		nome = "Biblioteca Virtual";
		biblioteca = Biblioteca.builder().nome(nome).build();
	}
	
	@Test
    public void givenBiblioteca_whenSave_thenGetOk() {
		bibliotecaRepository.save(biblioteca);
		
		Biblioteca result = bibliotecaRepository.findByNome(nome);
		
		assertEquals(nome, result.getNome());
    }
}
