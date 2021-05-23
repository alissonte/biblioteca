package com.biblioteca.desafio.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"livroId", "bibliotecaId"})
@Embeddable
public class EstoqueLivroKey implements Serializable{
	
	@Column(name = "livro_id")
	private Long livroId;
	
	@Column(name = "biblioteca_id")
	private Long bibliotecaId;
}
