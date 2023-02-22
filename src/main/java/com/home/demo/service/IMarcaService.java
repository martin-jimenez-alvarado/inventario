package com.home.demo.service;

import java.util.List;

import com.home.demo.entity.Marca;

public interface IMarcaService {
	public List<Marca> getAll();
	public boolean deleteByID(final Integer id);
	public boolean deleteByNombre(final String nombre);
	public boolean createUpdate(final Marca marca);
	public Marca findById(final Integer id);
	public Marca findByNombre(final String nombre);
}
