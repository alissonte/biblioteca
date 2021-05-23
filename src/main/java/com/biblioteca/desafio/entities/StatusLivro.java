package com.biblioteca.desafio.entities;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLivro {
	
	ALUGADO(1), DEVOLVIDO(2), REMOVIDO(3);
	
	int status;
	
	public static StatusLivro valueOf(Integer code) {
        return code == null ? null : Arrays.stream(StatusLivro.values())
                .filter(v -> v.status == code)
                .findFirst()
                .orElse(null);
    }
}
