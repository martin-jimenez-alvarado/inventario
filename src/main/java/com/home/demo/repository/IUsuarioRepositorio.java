package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.demo.entity.Usuario;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	public Usuario findByEmail(String email);
}
