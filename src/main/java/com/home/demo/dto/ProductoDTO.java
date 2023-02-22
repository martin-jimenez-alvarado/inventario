package com.home.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductoDTO implements Serializable {
	public ProductoDTO() {
		super();
	}

	private static final long serialVersionUID = 6632652195615729258L;

	@Schema(name = "id", example = "12", description = "Incluir el id para actualizar el producto", implementation = Integer.class)
	private Integer id;

	@Schema(name = "nombre", example = "Audifonos inalambricos", description = "Nombre del producto a registrar", implementation = String.class, defaultValue = "Audifonos")
	private String nombre;

	@Schema(name = "precio", example = "1500.85", description = "Precio del producto", defaultValue = "1854.48", implementation = Float.class)
	private float precio;

	@Schema(name = "categoria", description = "Id de la categoria")
	private CategoriaDTO categoria;

	@Schema(name = "detalles", description = "Lista de los detalles del producto")
	private List<ProductoDetalleDTO> detalles = new ArrayList<>();

}
