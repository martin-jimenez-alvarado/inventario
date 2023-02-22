package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.demo.entity.Marca;

@Repository
public interface IMarcaRepositorio extends JpaRepository<Marca, Integer>{
	public Marca findByNombre(String nombre);
	public void deleteByNombre(String nombre);
}
