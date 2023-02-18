package com.home.demo.service;

import java.util.List;
import com.home.demo.entity.Categoria;

public interface ICategoriaService {
	public List<Categoria> getAll();
	public Categoria getById(Integer id);
	public boolean deleteByName(String name);
	public boolean createUpdateCategory(Categoria categoria);
	public boolean deleteByID(Integer id);
}
