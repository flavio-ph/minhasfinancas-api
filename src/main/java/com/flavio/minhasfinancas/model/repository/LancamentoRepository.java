package com.flavio.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavio.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
}
