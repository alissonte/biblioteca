package com.biblioteca.desafio.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_library_book")
public class EstoqueLivro {
	
	@EmbeddedId
	private EstoqueLivroKey id;
	
	@ManyToOne
    @MapsId("bibliotecaId")
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    @ManyToOne
    @MapsId("livroId")
    @JoinColumn(name = "livro_id")
    private Livro livro;
    
    private Integer quantidade;
	
}
