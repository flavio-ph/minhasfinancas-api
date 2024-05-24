package com.flavio.minhasfinancas.model.repository.tests;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.flavio.minhasfinancas.model.entity.Usuario;
import com.flavio.minhasfinancas.model.repository.UsuarioRepository;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTests {
		


	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail () { 
		//cenário
		Usuario usuario = new Usuario();
		usuario.setNome("usuario");
		usuario.setEmail("usuario@email.com");
		
		//ação
		boolean resultado = repository.existsByEmail("usuario@email.com");
		
		//verificação
		Assertions.assertThat(resultado).isTrue();
		
	}
	
	@Test 
	public void deveRetornarFalsoQuantoNaoHouverUsuarioCadastradoComOemail() {
		//cenario
		repository.deleteAll();
		
		//acao
		boolean resultado = repository.existsByEmail("usuario@email.com");
		
		
		//verificacao
		Assertions.assertThat(resultado).isFalse();
		
		
	}
	
	
	@Test 
	public void devePersistirUmUsuarioNaBaseDeDaos () {
		Usuario usuario = criarUsuario();
		
		Usuario usuarioSalvo = repository.save(usuario);
		
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test 
	public void deveBuscarUmUsuarioPorEmail()  {
		
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		Optional<Usuario> resultado = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(resultado.isPresent()).isTrue();
	}
	
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase () {
		Optional <Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat (result.isPresent() ).isFalse();
		
	}

    public Usuario criarUsuario() {
  
    	Usuario usuario = new Usuario();
		usuario.setNome("usuario");
		usuario.setEmail("usuario@email.com");
		usuario.setSenha("senha");
		
		return usuario;
    

    	
    }
	
}
