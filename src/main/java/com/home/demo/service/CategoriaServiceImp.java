package com.home.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.demo.entity.Categoria;
import com.home.demo.repository.ICategoriaRepositorio;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class CategoriaServiceImp implements ICategoriaService{
	private ICategoriaRepositorio repoCategoria;

	public CategoriaServiceImp(ICategoriaRepositorio repoCategoria) {
		this.repoCategoria = repoCategoria;
	}

	@Override
	public List<Categoria> getAll() {
		return repoCategoria.findAll().size()>0 ? repoCategoria.findAll(Sort.by(Sort.Direction.ASC, "id")) : null;
	}

	@Override
	public Categoria getById(Integer id) {
		return repoCategoria.findById(id).orElse(null);
	}

	@Override
	public boolean deleteByID(Integer id) {
		repoCategoria.deleteById(id);
		return true;
	}

	@Override
	public boolean deleteByName(final String name) {
		Categoria categoria = repoCategoria.findByNombre(name);
		if(categoria != null) {
			repoCategoria.deleteById(categoria.getId());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean createUpdateCategory(Categoria categoria) {
		if(repoCategoria.findByNombre(categoria.getNombre()) == null)
			return repoCategoria.save(categoria) != null ? true: false;
		return false;
	}

}
