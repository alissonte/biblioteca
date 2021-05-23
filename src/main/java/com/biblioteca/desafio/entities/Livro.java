package com.biblioteca.desafio.entities;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biblioteca.desafio.convertes.StatusLivroConverter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tb_book")
public class Livro {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private Integer quantidade;
	
	private boolean disponivel;
	
	@Convert(converter = StatusLivroConverter.class)
	private StatusLivro status;
}
