package com.home.demo.service;

import java.util.List;

import com.home.demo.entity.Rol;

public interface IRolService {
	public Rol findByNombre(String nombre);
	public boolean removeByID(Integer id);
	public List<Rol> getAll();
	public Rol updateRol(Rol rol);
	public Rol findByID(Integer id);
}
