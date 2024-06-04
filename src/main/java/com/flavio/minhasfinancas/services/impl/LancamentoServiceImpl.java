package com.flavio.minhasfinancas.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavio.minhasfinancas.model.entity.Lancamento;
import com.flavio.minhasfinancas.model.enums.Status;
import com.flavio.minhasfinancas.model.enums.TipoDeLancamento;
import com.flavio.minhasfinancas.model.repository.LancamentoRepository;
import com.flavio.minhasfinancas.services.LancamentoService;
import com.flavio.minhasfinancas.services.exception.RegraNegocioException;


@Service
public class LancamentoServiceImpl implements LancamentoService {

	private LancamentoRepository repository;
	
	public LancamentoServiceImpl (LancamentoRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(Status.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
		
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
		
	}

	@Override
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example example = Example.of( lancamentoFiltro, 
				ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING) );
		
		return repository.findAll(example);
		
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, Status status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
		
	}


	@Override
	public Optional<Lancamento> obterPorId(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public void validar(Lancamento lancamento) {
		
		
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição valida");
		}
		
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12 ) {
			throw new RegraNegocioException("Informe um mês válido");
		}
		
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um ano válido");
		}
			
		if(lancamento.getUsuario()== null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Informe um Usuário");
		}
		
		if(lancamento.getValor()== null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um Valor válido.");
			
		}
		
		if(lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um tipo de Lançamento");
		}
	}


	
}




	

