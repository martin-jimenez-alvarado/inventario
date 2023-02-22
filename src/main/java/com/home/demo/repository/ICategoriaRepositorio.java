package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.demo.entity.Categoria;

@Repository
public interface ICategoriaRepositorio extends JpaRepository<Categoria, Integer>{
	public Categoria findByNombre(final String nombre);
	public Categoria deleteByNombre(final String nombre);
}
