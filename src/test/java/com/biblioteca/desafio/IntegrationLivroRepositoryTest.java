package com.biblioteca.desafio;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.desafio.entities.Livro;
import com.biblioteca.desafio.entities.StatusLivro;
import com.biblioteca.desafio.repository.LivroRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_METHOD)
public class IntegrationLivroRepositoryTest {

	@Autowired
	private LivroRepository livroRepository;
	
	private Livro livro;
	private String titulo;
	private StatusLivro status;
	
	
	@BeforeEach
	public void setup() {
		titulo = "Harry Potter";
		status = StatusLivro.DISPONIVEL;
		
		livro = Livro.builder()
				.status(status)
				.titulo(titulo)
				.build();
	}
	
	@Test
    public void givenLivro_whenSave_thenGetOk() {
		livroRepository.save(livro);
		
		Livro result = livroRepository.findByTitulo(titulo);
        assertEquals(status, result.getStatus());
        assertEquals(titulo, result.getTitulo());
    }
	
	@Test
	public void givenParams_WhenFind_theGetAllLivros() {
		livroRepository.save(livro);
		
		List<Livro> result = livroRepository.findAll();
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void givenParams_whenUpdate_thenGetNewLivroAtualizado() {
		StatusLivro novoStatus = StatusLivro.ALUGADO;
		livro.setStatus(novoStatus);

		livroRepository.save(livro);
		
		Livro novoLivro = livroRepository.findByTitulo(titulo);
		
		assertEquals(novoStatus, novoLivro.getStatus());
    }
	
	
	@Test	
    public void givenLivro_whenDelete_thenGetOk() {
		livroRepository.save(livro);
		
		Livro livroByTitulo = livroRepository.findByTitulo(titulo);
		
		livroRepository.delete(livroByTitulo);
		
		Livro result = livroRepository.findByTitulo(titulo);
		
		assertNull(result);
    }
}
