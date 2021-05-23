package com.biblioteca.desafio.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	
	public Usuario salvaNovoUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizaUsuario(Long id, Usuario usuario) {
		Usuario usuarioDb = usuarioRepository.getById(id);
		if(usuarioDb == null) {
			throw new EntityNotFoundException("Usuario não encontrado!");
		}
		
		usuarioDb.setEmail(usuario.getEmail());
		return usuarioRepository.save(usuarioDb);
	}
	
	public void deletaUsuario(Long id) {
		Usuario usuarioDb = usuarioRepository.getById(id);
		if(usuarioDb == null) {
			throw new EntityNotFoundException("Usuario não encontrado!");
		}
		usuarioRepository.deleteById(id);
	}
}
