package com.biblioteca.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroBibliotecaDTO {
	
	private Long idBiblioteca;
	
	private String titulo;
	
	private String categoria;
	
	private Integer quantidade;
}
