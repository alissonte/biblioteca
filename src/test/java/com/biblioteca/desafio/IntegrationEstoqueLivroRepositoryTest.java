package com.biblioteca.desafio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.desafio.entities.Biblioteca;
import com.biblioteca.desafio.entities.EstoqueLivro;
import com.biblioteca.desafio.entities.EstoqueLivroKey;
import com.biblioteca.desafio.entities.Livro;
import com.biblioteca.desafio.entities.StatusLivro;
import com.biblioteca.desafio.repository.BibliotecaRepository;
import com.biblioteca.desafio.repository.EstoqueLivroRepository;
import com.biblioteca.desafio.repository.LivroRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_METHOD)
public class IntegrationEstoqueLivroRepositoryTest {
	
	@Autowired
	private EstoqueLivroRepository estoqueLivroRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	
	
	@Test
    public void givenBiblioteca_whenSave_thenGetOk() {
		Livro livro1 = Livro.builder().status(StatusLivro.DISPONIVEL).titulo("A").build();
		Livro livro2 = Livro.builder().status(StatusLivro.ALUGADO).titulo("B").build();
		List<Livro> livros = Arrays.asList(livro1, livro2);
		
		Biblioteca biblioteca1 = Biblioteca.builder().nome("B1").build();
		Biblioteca biblioteca2 = Biblioteca.builder().nome("B2").build();
		List<Biblioteca> bibliotecas = Arrays.asList(biblioteca1, biblioteca2);
		
		//save livros
		List<Livro> saveAllLivros = livroRepository.saveAll(livros);
		
		//save biblioteca
		List<Biblioteca> saveAllBibli = bibliotecaRepository.saveAll(bibliotecas);
		
		//save estoque1
		EstoqueLivroKey estoqueKey1 = EstoqueLivroKey.builder()
				.bibliotecaId(saveAllBibli.get(0).getId())
				.livroId(saveAllLivros.get(0).getId()).build();
		
		EstoqueLivro estoqueLivro1 = EstoqueLivro.builder()
				.id(estoqueKey1)
				.biblioteca(saveAllBibli.get(0))
				.livro(saveAllLivros.get(0))
				.quantidade(10)
				.build();
		
		
		EstoqueLivro resultEstoqueLivro = estoqueLivroRepository.save(estoqueLivro1);
		assertEquals(10, resultEstoqueLivro.getQuantidade().intValue());
    }
}
