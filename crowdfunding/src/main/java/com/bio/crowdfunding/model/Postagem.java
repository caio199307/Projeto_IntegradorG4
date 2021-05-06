package com.bio.crowdfunding.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Postagem {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id; 
	 
	@NotNull
	@Size (max = 100)
	private String titulo;
	
	@NotNull
	private String descricao;
	
	private String midia;
	
	@NotNull
	private double meta;
	
	@NotNull
	private Date data;
	
	private int qtde_doacao;
	
	private double valor_doado;
	
	private int qtde_voluntario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMidia() {
		return midia;
	}

	public void setMidia(String midia) {
		this.midia = midia;
	}

	public double getMeta() {
		return meta;
	}

	public void setMeta(double meta) {
		this.meta = meta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQtde_doacao() {
		return qtde_doacao;
	}

	public void setQtde_doacao(int qtde_doacao) {
		this.qtde_doacao = qtde_doacao;
	}

	public double getValor_doado() {
		return valor_doado;
	}

	public void setValor_doado(double valor_doado) {
		this.valor_doado = valor_doado;
	}

	public int getQtde_voluntario() {
		return qtde_voluntario;
	}

	public void setQtde_voluntario(int qtde_voluntario) {
		this.qtde_voluntario = qtde_voluntario;
	}
	
}
