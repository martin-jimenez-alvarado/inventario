package com.home.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Usuario {
	
	public Usuario() {
		super();
	}
	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Usuario(String email, String password, Set<Rol> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public void anhadirRol(Rol rol) {
		this.roles.add(rol);
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45, nullable = false, unique = true)
	private String email;
	
	@Column(length = 10, nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", 
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
}
