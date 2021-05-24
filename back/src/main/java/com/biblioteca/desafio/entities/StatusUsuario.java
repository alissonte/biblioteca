package com.biblioteca.desafio.entities;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StatusUsuario {
	
	ATIVO(1), INATIVO(2);
	
	int status;
	
	StatusUsuario(int status) {
		this.status = status;
	}
	
	public static StatusUsuario valueOf(Integer code) {
        return code == null ? null : Arrays.stream(StatusUsuario.values())
                .filter(v -> v.status == code)
                .findFirst()
                .orElse(null);
    }

}
