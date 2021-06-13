package com.bio.crowdfunding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bio.crowdfunding.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
	@Query(value = "SELECT p FROM Postagem p WHERE p.usuario.nome like '% :nomeUsuario '")
	public List<Postagem> findPostagemByNomeUsuario(String nomeUsuario);
	
	@Query(value = "SELECT t FROM Tema t WHERE t.tema.id = :id")
	public List<Postagem> findPostagemByTema(Long tema);
}
