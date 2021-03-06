package com.bio.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@URL
	private String midia;
	
	private double valor_doado;
		
	@NotNull
	private double meta;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;

	
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

	public double getValor_doado() {
		return valor_doado;
	}

	public void setValor_doado(Double valor_doado) {
		this.valor_doado = valor_doado;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
