package com.home.demo.entity;

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
public class ArticuloCarrito {

	public ArticuloCarrito(){
		super();
	}

	public ArticuloCarrito(int cantidad, Producto producto, Usuario usuario) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.usuario = usuario;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int cantidad;


	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;


	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}



