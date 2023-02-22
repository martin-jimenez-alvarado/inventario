package com.home.demo.dto;

import java.util.List;

import com.home.demo.entity.Rol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioDTO {
	@Schema(name = "id", example = "3", description = "Identificador del usuario si se va actualizar")
	private Integer id;
	
	@Schema(name = "email", example = "email@example.tld", description = "Email con que se registr el usuario", minLength = 6, maxLength = 45)
	private String email;
	
	@Schema(name = "password", example = "password", description = "Password para acceder(sin cifrar :/)", minLength = 1, maxLength = 10)
	private String password;
	
	@Schema(name = "roles", description = "Roles a los que pertenece el usuario")
	private List<Rol> roles;
}
