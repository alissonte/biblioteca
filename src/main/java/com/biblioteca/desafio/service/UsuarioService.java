package com.biblioteca.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.desafio.entities.Usuario;
import com.biblioteca.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> retornaTodosUsuarios() {
		return usuarioRepository.findAll();
	}

}
