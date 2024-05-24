package com.flavio.minhasfinancas.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavio.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	boolean existsByEmail(String email); //método para verificar se o email já está cadastrado
	
	Optional<Usuario> findByEmail (String email);
	
}
