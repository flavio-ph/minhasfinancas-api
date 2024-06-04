package com.flavio.minhasfinancas.model.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flavio.minhasfinancas.model.entity.Lancamento;
import com.flavio.minhasfinancas.model.enums.TipoDeLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
	
}
