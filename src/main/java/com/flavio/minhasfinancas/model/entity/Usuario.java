package com.flavio.minhasfinancas.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Setter;

@Builder
@Entity //faz com que o jpa reconheça essa classe como um mapeamento de uma entidade
@Table( name = "usuario", schema = "financas")
public class Usuario {
	
	
	@Id
	@Column (name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column (name = "nome")
	private String nome;
	
	
	@Column (name = "email")
	@Setter
	private String email;
	
	
	@Column (name = "senha")
	private String senha;
	


	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}


	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object obterPorId(Usuario usuario) {
		
		return null;
	}





}
