package com.flavio.minhasfinancas.services;

import java.util.Optional;

import com.flavio.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar (String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario); 
	
	void validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);

	


	
}
