package com.home.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.home.demo.entity.Rol;
import com.home.demo.repository.IRolRepositorio;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RolServiceImp implements IRolService {
	private IRolRepositorio repoRol;

	public RolServiceImp(IRolRepositorio repoRol) {
		this.repoRol = repoRol;
	}

	@Override
	public Rol findByNombre(String nombre) {
		return repoRol.findByNombre(nombre);
	}

	@Override
	public boolean removeByID(Integer id) {
		if (repoRol.findById(id) != null) {
			repoRol.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public List<Rol> getAll() {
		return repoRol.findAll();
	}

	@Override
	public Rol updateRol(Rol rol) {
		if (rol.getId() != null) {
			Rol r = repoRol.findById(rol.getId()).orElse(null);
			if (r != null) {
				r.setNombre(rol.getNombre());
				log.info("___actualiza nombre_");
				return repoRol.save(r);
			}
		}else {
			Rol r = repoRol.findByNombre(rol.getNombre());
			if(r == null)
				return repoRol.save(rol);
			else
				return r;
		}
		return null;
	}
	
	
	@Override
	public Rol findByID(Integer id) {
		return repoRol.findById(id).orElse(null);
	}

}
