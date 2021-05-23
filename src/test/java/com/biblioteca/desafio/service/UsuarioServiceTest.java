package com.biblioteca.desafio.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.biblioteca.desafio.entities.Usuario;
import com.biblioteca.desafio.repository.UsuarioRepository;

@RunWith(JUnitPlatform.class)
class UsuarioServiceTest {
	
	@InjectMocks
	private UsuarioService usuarioService;	

	@Mock
	private UsuarioRepository usuarioRepository;
	
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testRetornaTodosUsuarios() {
		List<Usuario> usuarios = criaUsuarios();
		
		when(usuarioRepository.findAll()).thenReturn(usuarios);
		
		List<Usuario> resultado = usuarioService.retornaTodosUsuarios();
		
		verify(usuarioRepository, only()).findAll();
		assertTrue(resultado.size() == 2);
	}

	private List<Usuario> criaUsuarios() {
		Usuario usuario1 = Usuario
					.builder()
					.email("teste1@gmail.com")
				.build();
		
		Usuario usuario2 = Usuario
				.builder()
				.email("teste2@gmail.com")
			.build();
		
		
		return Arrays.asList(usuario1, usuario2);
	}

}
