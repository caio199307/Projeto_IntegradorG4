package com.bio.crowdfunding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bio.crowdfunding.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//UserName neste caso foi igual ao email
	Optional<Usuario> findByUsuario(String usuario);
}
