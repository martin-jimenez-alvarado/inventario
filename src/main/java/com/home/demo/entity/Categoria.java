package com.home.demo.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categoria implements Serializable{
	private static final long serialVersionUID = -5068807765309957228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45, nullable = false, unique = true )
	@Schema(name = "categoria", description = "Nombre de la categoria a crear", minLength = 0, maxLength = 45, example = "Gadgets")
	private String nombre;

	public Categoria () {
		super();
	}

	public Categoria(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Categoria(Integer id, String nombre, Marca marca) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
	}

	@JsonIgnoreProperties("categorias")
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@Override
	public String toString() {
		return nombre;
	}

}
