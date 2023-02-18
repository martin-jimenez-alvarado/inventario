package com.home.demo.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.home.demo.entity.Marca;
import com.home.demo.repository.IMarcaRepositorio;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MarcaServiceImp implements IMarcaService {
	private IMarcaRepositorio repoMarca;

	public MarcaServiceImp(IMarcaRepositorio repoMarca) {
		this.repoMarca = repoMarca;
	}
	
	@Override
	public List<Marca> getAll(){
		return repoMarca.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	

	@Override
	public boolean deleteByID(Integer id) {
		repoMarca.deleteById(id);
		return true;
	}

	
	@Override
	public boolean createUpdate(Marca marca, Integer id) {
		
		log.info(marca.toString());
		
		Marca m = findById(id);
		if(m != null) {
			m.setNombre(marca.getNombre());
			m.setCategorias(marca.getCategorias());
			m.setId(id);
			repoMarca.save(marca);
		}
		else {
			repoMarca.save(marca);
		}
		return true;
	}

	
	@Override
	public Marca findById(Integer id) {
		return repoMarca.findById(id).orElse(null);
	}

}
