package com.biblioteca.desafio.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.desafio.dto.LivroBibliotecaDTO;
import com.biblioteca.desafio.entities.Biblioteca;
import com.biblioteca.desafio.entities.Categoria;
import com.biblioteca.desafio.entities.Emprestimo;
import com.biblioteca.desafio.entities.EstoqueLivro;
import com.biblioteca.desafio.entities.EstoqueLivroKey;
import com.biblioteca.desafio.entities.Livro;
import com.biblioteca.desafio.entities.StatusLivro;
import com.biblioteca.desafio.entities.Usuario;
import com.biblioteca.desafio.repository.BibliotecaRepository;
import com.biblioteca.desafio.repository.EmprestimoRepository;
import com.biblioteca.desafio.repository.EstoqueLivroRepository;
import com.biblioteca.desafio.repository.LivroRepository;
import com.biblioteca.desafio.repository.UsuarioRepository;

@Service
public class BibliotecaService {
	
	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EstoqueLivroRepository estoqueLivroRepository;
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	
	public List<Livro> recuperaLivrosBiblioteca() {
		List<Biblioteca> findAll = bibliotecaRepository.findAll();
		Set<Livro> estoque = findAll.stream()
				.<Livro>flatMap(b -> b.getLivros().stream().map(l -> l.getLivro()))
				.sorted(Comparator.comparing(Livro::getStatus))
				.collect(Collectors.toSet());
		
		return new ArrayList<>(estoque);
	}


	@Transactional
	public Livro saveNovoLivroBiblioteca(Long idBiblioteca, LivroBibliotecaDTO livroBibliotecaDTO) {		
		Biblioteca biblioteca = bibliotecaRepository.getById(idBiblioteca);
		Livro livro = salvaNovoLivro(livroBibliotecaDTO);
		
		salvaNovoEstoqueBiblioteca(biblioteca, livro, livroBibliotecaDTO.getQuantidade());
		
		return livro;
	}
	
	
	public void deletaLivroBiblioteca(Long idBiblioteca, Long idLivro) {
		EstoqueLivroKey estoqueKey = EstoqueLivroKey.builder().bibliotecaId(idBiblioteca).livroId(idLivro).build();
		estoqueLivroRepository.deleteById(estoqueKey);
	}
	
	@Transactional
	public void emprestimoLivroBiblioteca(Long idBiblioteca, Long idLivro, String email) {
		Livro livro = livroRepository.getById(idLivro);
		Usuario usuario = usuarioRepository.findByEmail(email);
		Biblioteca biblioteca = bibliotecaRepository.getById(idBiblioteca);
		
		saveNovoEmprestimo(livro, usuario);
		atualizaEstoqueBiblioteca(biblioteca, livro);
	}


	private void saveNovoEmprestimo(Livro livro, Usuario usuario) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus3Days = now.plusDays(3);
		
		Emprestimo emprestimo = Emprestimo.builder()
				.dataEmprestimo(now)
				.dataPrevistaRetorno(plus3Days)
				.livro(livro)
				.usuario(usuario)
				.build();
		
		emprestimoRepository.save(emprestimo);
	}

	private void salvaNovoEstoqueBiblioteca(Biblioteca biblioteca, Livro livro, Integer quantidade) {
		EstoqueLivroKey estoqueKey = EstoqueLivroKey.builder()
				.bibliotecaId(biblioteca.getId())
				.livroId(livro.getId())
				.build();
		
		EstoqueLivro estoqueLivro = EstoqueLivro.builder()
				.id(estoqueKey)
				.biblioteca(biblioteca)
				.livro(livro)
				.quantidade(quantidade)
				.build();
		
		estoqueLivroRepository.save(estoqueLivro);
	}
	
	private void atualizaEstoqueBiblioteca(Biblioteca biblioteca, Livro livro) {
		livro.setStatus(StatusLivro.ALUGADO);
		
		EstoqueLivroKey estoqueKey = EstoqueLivroKey.builder()
				.bibliotecaId(biblioteca.getId())
				.livroId(livro.getId())
				.build();
		
		EstoqueLivro estoqueLivro = estoqueLivroRepository.getById(estoqueKey);
		if(Objects.isNull(estoqueLivro)) {
			throw new EntityNotFoundException("Não existe nenhum livro na biblioteca!;");
		} else {
			estoqueLivro.setQuantidade((estoqueLivro.getQuantidade() - 1));
			estoqueLivroRepository.save(estoqueLivro);
		}
	}

	private Livro salvaNovoLivro(LivroBibliotecaDTO livroBibliotecaDTO) {
		Livro livro = Livro.builder()
				.categoria(Categoria.valueOf(livroBibliotecaDTO.getCategoria()))
				.status(StatusLivro.DISPONIVEL)
				.titulo(livroBibliotecaDTO.getTitulo())
				.build();
		
		livro = livroRepository.save(livro);
		return livro;
	}
}
