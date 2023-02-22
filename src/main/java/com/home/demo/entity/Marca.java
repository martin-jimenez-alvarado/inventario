package com.home.demo.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class Marca implements Serializable {
	private static final long serialVersionUID = -2708010202632381145L;


	public Marca() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Schema(required = true, maxLength = 40, minLength = 1, description = "Marca de los articulos", example = "ATVIO")
	@Column(length=40, nullable=false, unique=true)
	private String nombre;


	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "marca_id")
	private List<Categoria> categorias;


	public Marca(String nombre, List<Categoria> categorias) {
		this.nombre = nombre;
		this.categorias = categorias;
	}


}
