package com.biblioteca.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.desafio.common.BibliotecaErrorException;
import com.biblioteca.desafio.entities.StatusUsuario;
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
		Usuario usuarioDb = usuarioRepository.findById(id)
				.orElseThrow(() -> new BibliotecaErrorException("Usuario não encontrado!"));
		
		usuarioDb.setEmail(usuario.getEmail());
		return usuarioRepository.save(usuarioDb);
	}
	
	public void deletaUsuario(Long id) {
		Usuario usuarioDb = usuarioRepository.findById(id)
				.orElseThrow(() -> new BibliotecaErrorException("Usuario não encontrado!"));
		
		usuarioRepository.deleteById(id);
	}
	
	public void inativarUsuario(Long id) {
		Usuario usuarioDb = usuarioRepository.findById(id)
				.orElseThrow(() -> new BibliotecaErrorException("Usuario não encontrado!"));
		
		usuarioDb.setStatus(StatusUsuario.INATIVO);
		
		atualizaUsuario(id, usuarioDb);
	}
	
	public void ativarUsuario(Long id) {
		Usuario usuarioDb = usuarioRepository.findById(id)
				.orElseThrow(() -> new BibliotecaErrorException("Usuario não encontrado!"));
		
		usuarioDb.setStatus(StatusUsuario.ATIVO);
		
		atualizaUsuario(id, usuarioDb);
	}
}
