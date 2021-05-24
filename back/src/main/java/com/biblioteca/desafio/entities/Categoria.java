package com.biblioteca.desafio.entities;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Categoria { 
	
	INFORMATICA(100, "Informática"), CIENCIAS(200, "Ciências");
	
	int codigo;
	
	String descricao;
	
	Categoria(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Categoria valueOf(Integer code) {
        return code == null ? null : Arrays.stream(Categoria.values())
                .filter(v -> v.codigo == code)
                .findFirst()
                .orElse(null);
    }

}
