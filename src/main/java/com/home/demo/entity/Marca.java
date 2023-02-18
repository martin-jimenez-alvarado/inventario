package com.home.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
public class Marca {
	
	public Marca() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=40, nullable=false, unique=true)
	private String nombre;
	
	@OneToMany
	@JoinColumn(name = "marca_id")
	private List<Categoria> categorias;
	
	
	public Marca(String nombre, List<Categoria> categorias) {
		this.nombre = nombre;
		this.categorias = categorias;
	}


	@Override
	public String toString() {
		return "Marca [nombre=" + nombre + "]";
	}
	
	
}
