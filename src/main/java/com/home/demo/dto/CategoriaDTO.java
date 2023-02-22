package com.home.demo.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoriaDTO implements Serializable{
	public CategoriaDTO() {
		super();
	}
	private static final long serialVersionUID = 9200607973719980822L;
	
	@Schema(name = "id", description = "Identificador obligatorio al actualizar la categoria, vacio si se va a crear una nueva categoria", defaultValue = "0", example = "2", implementation = Integer.class)
	private Integer id;
	
	@Schema(name = "nombre", description = "Nombre de la categoria", defaultValue = "Linea Blanca", example = "Lavadora", implementation=String.class)
	private String nombre;

}
