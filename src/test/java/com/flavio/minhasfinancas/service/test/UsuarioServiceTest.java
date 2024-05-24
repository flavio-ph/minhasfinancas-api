package com.flavio.minhasfinancas.service.test;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.flavio.minhasfinancas.model.entity.Usuario;
import com.flavio.minhasfinancas.model.repository.UsuarioRepository;
import com.flavio.minhasfinancas.services.UsuarioService;
import com.flavio.minhasfinancas.services.exception.ErroAutenticacao;
import com.flavio.minhasfinancas.services.exception.RegraNegocioException;
import com.flavio.minhasfinancas.services.impl.UsuarioServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;

	@MockBean
	UsuarioRepository repository;
	
	
	@Test (expected = Test.None.class)
	public void deveSalvarUmUsuario () {
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = new Usuario();
		usuario.setNome("usuario");
		usuario.setEmail("usuario@email.com");
		usuario.setSenha("senha");
		usuario.setId(1l);
		
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l).isNotNull();
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@emial.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
		
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void NaoDeveSalvarUmUsuarioComEmailJaCadastrado () {
		String email = "email@email.com";
		Usuario usuario = new Usuario();
		usuario.setEmail("usuario@email.com");
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);
		
		service.salvarUsuario(usuario);
		
		Mockito.verify(repository, Mockito.never() ).save(usuario);
	}
	
	@Before
	public void setUp () {
		Mockito.spy(UsuarioServiceImpl.class);
		
		service = new UsuarioServiceImpl(repository);
		
	}
	
	
	@Test(expected = Test.None.class)
	public void deveAutenticarUmUsuarioComSucesso() {
		//cenario
		String email = "email@email.com";
		String senha = "senha";
		
		Usuario usuario = new Usuario();
		usuario.setNome("usuario");
		usuario.setEmail("usuario@email.com");
		usuario.setSenha("senha");
		usuario.setId(1l);
		
		Mockito.when(repository.findByEmail(email) ).thenReturn(Optional.of(usuario));
		
		//acao
		Usuario resultado = service.autenticar(email, senha);
		
		//verificacao
		Assertions.assertThat(resultado).isNotNull();
	}
	
	@Test
	public void deveLancarErroQuandoNaoEcontrarUsuarioCadastradoComOEmailCadastrado () {
		//cenario 
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("email@email.com", "senha") );
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Usuário não encontrado para o email informado!");
	}
	
	@Test
	public void deveLancarErroQuandoSenhaNaoBater () {
		//cenario
		String senha = "senha";
		Usuario usuario = new Usuario();
		usuario.setEmail("usuario@email.com");
		usuario.setSenha("senha");
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		//acao
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123")); 
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("a");
		
	}	
	
	
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail () {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("email@email.com");
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroQuandoExistirEmailCadastrado () {
		//cenario 
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		//acao
		service.validarEmail("email@email.com");
	}
	
	
}
