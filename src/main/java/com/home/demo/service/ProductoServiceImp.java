package com.home.demo.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.home.demo.entity.Producto;
import com.home.demo.repository.IProductoRepositorio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoServiceImp implements IProductoService{
	private IProductoRepositorio repoProducto;
	
	public ProductoServiceImp(IProductoRepositorio repoProducto){
		this.repoProducto = repoProducto;
	}
	
	@Override
	public List<Producto> getAll() {
		return repoProducto.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	@Override
	public Producto findById(Integer id) {
		return repoProducto.findById(id).orElse(null);
	}

	@Override
	public boolean createUpdate(Producto producto) {
		Producto p = repoProducto.findByNombre(producto.getNombre());
		if(p != null) {
			p.setNombre(producto.getNombre());
			p.setPrecio(producto.getPrecio());
			p.setCategoria(producto.getCategoria());
			repoProducto.save(p);
		}
		else {
			repoProducto.save(producto);
		}
		return true;
	}

	@Override
	public boolean deleteByID(Integer id) {
		Producto producto = findById(id);
		log.info("...::::" + producto.getNombre());
		if(producto != null) {
			repoProducto.deleteById(id);
			return true;
		}
		else
			return false;
	}

}
