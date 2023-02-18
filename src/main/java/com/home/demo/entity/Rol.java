package com.home.demo.entity;

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
public class Rol {
	
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
	
	@Column(length=25, nullable=false, unique=true)
	private String nombre;

	@Override
	public String toString() {
		return nombre;
	}

	
}

