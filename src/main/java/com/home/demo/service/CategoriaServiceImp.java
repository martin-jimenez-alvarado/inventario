package com.home.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.home.demo.entity.Categoria;
import com.home.demo.repository.ICategoriaRepositorio;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
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
	public boolean deleteByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createUpdateCategory(Categoria categoria) {
		return (repoCategoria.save(categoria) != null) ? true: false;
	}

}
