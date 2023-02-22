package com.home.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Producto {
	public Producto() {
		super();
	}

	public Producto(String nombre, float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public void setDetalle(Integer id, String nombre, String valor) {
		this.detalles.add(new ProductoDetalle(id, nombre, valor, this));
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=128, nullable = false, unique=true)
	private String nombre;

	private float precio;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@JsonIgnoreProperties("producto")
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<ProductoDetalle> detalles = new ArrayList<>();

	public void anhadirDetalles(final String nombre, final String valor) {
		this.detalles.add(new ProductoDetalle(nombre, valor, this));
	}

}
