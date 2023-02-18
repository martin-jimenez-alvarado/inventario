package com.home.demo.service;

import java.util.List;
import com.home.demo.entity.Marca;

public interface IMarcaService {
	public List<Marca> getAll();
	public boolean deleteByID(Integer id);
	public boolean createUpdate(Marca marca, Integer id);
	public Marca findById(Integer id);
}
