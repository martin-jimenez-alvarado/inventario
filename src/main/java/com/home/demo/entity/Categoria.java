package com.home.demo.entity;

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
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45, nullable = false, unique = true )
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
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	Marca marca;

	@Override
	public String toString() {
		return nombre;
	}
	
}
