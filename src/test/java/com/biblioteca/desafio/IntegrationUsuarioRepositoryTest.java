package com.biblioteca.desafio;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.desafio.entities.Role;
import com.biblioteca.desafio.entities.Usuario;
import com.biblioteca.desafio.repository.UsuarioRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_METHOD)
public class IntegrationUsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	private String email;
	private LocalDateTime dataCriacao;
	private Set<Role> roles;
	
	@BeforeEach
	public void setup() {
		email = "teste@gmail.com";
		dataCriacao = LocalDateTime.now();
		roles = new HashSet<>();
		usuario = Usuario.builder()
				.email(email)
				.dataCriacao(dataCriacao)
				.roles(roles)
				.build();
	}
	
	@Test
    public void givenUsuario_whenSave_thenGetOk() {
        usuarioRepository.save(usuario);
        
        Usuario result = usuarioRepository.findByEmail(email);
        assertEquals(email, result.getEmail());
        assertEquals(dataCriacao, result.getDataCriacao());
        assertEquals(roles.size(), result.getRoles().size());
    }
	
	@Test
	public void givenParams_WhenFind_theGetAllUsuarios() {
		usuarioRepository.save(usuario);
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		assertEquals(usuarios.size(), 1);
	}
	
	@Test
	public void givenParams_whenUpdate_thenGetNewUsuarioAtualizado() {
		String novoEmail = "alissonte@test.com";
        usuarioRepository.save(usuario);
        
        Usuario usuario1 = usuarioRepository.findByEmail(email);
        
        usuario1.setEmail(novoEmail);
        usuarioRepository.save(usuario1);
        
        Usuario usuario2 = usuarioRepository.findByEmail(novoEmail);
        
        assertEquals(novoEmail, usuario2.getEmail());
        assertEquals(dataCriacao, usuario2.getDataCriacao());
        assertEquals(roles.size(), usuario2.getRoles().size());
    }
	
	
	@Test	
    public void givenUsuario_whenDelete_thenGetOk() {
		usuarioRepository.save(usuario);
        
        Usuario usuarioByEmail = usuarioRepository.findByEmail(email);
        
        usuarioRepository.delete(usuarioByEmail);
        
        Usuario result = usuarioRepository.findByEmail(email);
        
        assertNull(result);
    }
}
