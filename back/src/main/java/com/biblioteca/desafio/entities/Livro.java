package com.biblioteca.desafio.entities;

import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.biblioteca.desafio.convertes.StatusLivroConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tb_book")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@Convert(converter = StatusLivroConverter.class)
	private StatusLivro status;
	
	@OneToMany(mappedBy = "livro")
    Set<EstoqueLivro> livros;
}
