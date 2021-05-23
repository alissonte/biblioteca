package com.biblioteca.desafio.entities;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLivro {
	
	DISPONIVEL(1), ALUGADO(2), DEVOLVIDO(3), REMOVIDO(4);
	
	int status;
	
	public static StatusLivro valueOf(Integer code) {
        return code == null ? null : Arrays.stream(StatusLivro.values())
                .filter(v -> v.status == code)
                .findFirst()
                .orElse(null);
    }
}
