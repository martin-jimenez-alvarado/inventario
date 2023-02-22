package com.home.demo.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RolDTO  implements Serializable{
	private static final long serialVersionUID = -6263020142610914548L;

	@Schema(name = "id", example = "3", description = "Identificador del rol")
	private Integer id;
	
	@Schema(name = "nombre", example = "Anonymous", description = "Nombre del rol", maxLength = 25)
	private String nombre;
}
