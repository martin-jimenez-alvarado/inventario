package com.home.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "producto_detalles")
@Getter
@Setter
@Slf4j
public class ProductoDetalle {
	public ProductoDetalle() {
		super();
	}

	public ProductoDetalle(Integer id, String nombre, String valor, Producto producto) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.producto = producto;
	}

	public ProductoDetalle(String nombre, String valor, Producto producto) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.producto = producto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45, nullable = false)
	private String nombre;

	@Column(length = 45, nullable = false)
	private String valor;

	@JsonIgnoreProperties("detalles")
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Override
	public String toString() {
		return nombre + " - " + valor;
	}
}
