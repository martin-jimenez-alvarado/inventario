package com.home.demo.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rol implements Serializable {
	private static final long serialVersionUID = 7163837628235069088L;

	public Rol() {
		super();
	}

	public Rol(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Schema(name = "nombre", example = "Administrador", description = "Nombre del rol a definir")
	@Column(length=25, nullable=false, unique=true)
	private String nombre;

	@Override
	public String toString() {
		return  nombre ;
	}


}

