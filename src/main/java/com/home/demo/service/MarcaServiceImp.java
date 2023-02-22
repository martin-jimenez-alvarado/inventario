package com.home.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.demo.entity.Marca;
import com.home.demo.repository.IMarcaRepositorio;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MarcaServiceImp implements IMarcaService {
	private IMarcaRepositorio repoMarca;

	public MarcaServiceImp(IMarcaRepositorio repoMarca) {
		this.repoMarca = repoMarca;
	}

	@Override
	public List<Marca> getAll() {
		return repoMarca.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public boolean deleteByID(Integer id) {
		repoMarca.deleteById(id);
		return true;
	}

	@Override
	public boolean createUpdate(Marca marca) {
		log.info(marca.toString());
		if (marca.getId() != null) {
			Marca m = findById(marca.getId());
			if (m != null) {
				m.setNombre(marca.getNombre());
				m.setCategorias(marca.getCategorias());
				repoMarca.save(marca);
			}
			return true;
		}
		else if(findByNombre(marca.getNombre()) == null) {
			repoMarca.save(marca);
			return true;
		}
		else return false;
	}

	@Override
	public Marca findById(Integer id) {
		return repoMarca.findById(id).orElse(null);
	}

	@Override
	public boolean deleteByNombre(String nombre) {
		Marca marca = repoMarca.findByNombre(nombre);
		if (marca != null) {
			repoMarca.deleteByNombre(nombre);
			return true;
		} else
			return false;
	}

	@Override
	public Marca findByNombre(final String nombre) {
		return repoMarca.findByNombre(nombre);
	}

}
