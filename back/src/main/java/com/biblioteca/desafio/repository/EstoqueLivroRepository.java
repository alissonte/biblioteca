package com.biblioteca.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.desafio.entities.EstoqueLivro;
import com.biblioteca.desafio.entities.EstoqueLivroKey;

@Repository
public interface EstoqueLivroRepository extends JpaRepository<EstoqueLivro, EstoqueLivroKey>{
}
