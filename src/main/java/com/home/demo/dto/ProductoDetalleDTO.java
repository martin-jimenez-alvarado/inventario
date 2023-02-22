package com.home.demo.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductoDetalleDTO implements Serializable{
	private static final long serialVersionUID = -4835099096305452844L;

	@Schema(name = "nombre", description = "Nombre del detalle", example = "detalle1", implementation = String.class)
	private String nombre;
	
	@Schema(name = "valor", description = "Valor del producto", example = "Cantidad de dinero o piezas/capacidad", implementation = String.class)
	private String valor;
}
