package com.flavio.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.flavio.minhasfinancas.model.enums.Status;
import com.flavio.minhasfinancas.model.enums.TipoDeLancamento;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "lancamento", schema = "financas")
public class Lancamento {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	

	@Column (name = "mes")
	private int mes;
	
	@Column (name = "name")
	private Integer ano;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario") //a anotação JoinColumn indica que a coluna em questão tem relacionamento entre entidades,no caso entre as entidades relacionamento e usuario;
	private Usuario usuario;
	
	@Column (name = "valor")
	private BigDecimal valor;
	
	@Column (name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate DataCadastro;
	
	@Column (name = "tipo")
	@Enumerated (value = EnumType.STRING)
	private TipoDeLancamento tipo;
	
	@Column(name = "estatus")
	@Enumerated(value = EnumType.STRING)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public TipoDeLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeLancamento tipo) {
		this.tipo = tipo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DataCadastro, ano, descricao, id, mes, status, tipo, usuario, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(DataCadastro, other.DataCadastro) && ano == other.ano
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id) && mes == other.mes
				&& status == other.status && tipo == other.tipo && Objects.equals(usuario, other.usuario)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", mes=" + mes + ", ano=" + ano + ", usuario="
				+ usuario + ", valor=" + valor + ", DataCadastro=" + DataCadastro + ", tipo=" + tipo + ", status="
				+ status + "]";
	} 
	
	
	
}
