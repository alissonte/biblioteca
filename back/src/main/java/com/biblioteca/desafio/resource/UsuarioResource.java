package com.biblioteca.desafio.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.desafio.entities.Usuario;
import com.biblioteca.desafio.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> recuperaTodosUsuarios(){
		List<Usuario> usuarios = usuarioService.retornaTodosUsuarios();
		
		return ResponseEntity.ok().body(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvaNovoUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.salvaNovoUsuario(usuario));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> atualizaUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.atualizaUsuario(id, usuario));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity deletaUsuario(@PathVariable Long id) {
		usuarioService.deletaUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
}
