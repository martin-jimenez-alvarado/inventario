package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.home.demo.entity.Producto;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer>{
	public void deleteByNombre(String name);
	public Producto findByNombre(String name);
}
