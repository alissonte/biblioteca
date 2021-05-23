package com.biblioteca.desafio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_METHOD)
public class IntegrationBiliotecaRepositoryTest {
	
	
	@BeforeEach
	public void setup() {
		
	}
	
	@Test
    public void givenLivro_whenSave_thenGetOk() {
		
    }
	
	@Test
	public void givenParams_WhenFind_theGetAllLivros() {
		
	}
	
	@Test
	public void givenParams_whenUpdate_thenGetNewLivroAtualizado() {
		
    }
	
	
	@Test	
    public void givenLivro_whenDelete_thenGetOk() {
		
    }
}
