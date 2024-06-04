package com.flavio.minhasfinancas.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.flavio.minhasfinancas.model.entity.Lancamento;
import com.flavio.minhasfinancas.model.enums.Status;

public interface LancamentoService {

	Lancamento salvar (Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List <Lancamento> buscar ( Lancamento LancamentoFiltro );
	
	void atualizarStatus(Lancamento lancamento, Status status);
	
	void validar(Lancamento lancamento);
	
	Optional<Lancamento> obterPorId(Long id);
	

	
}
