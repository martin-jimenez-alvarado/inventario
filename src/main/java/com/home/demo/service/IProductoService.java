package com.home.demo.service;

import java.util.List;
import com.home.demo.dto.ProductoDTO;
import com.home.demo.entity.Producto;


public interface IProductoService {
	public List<Producto> getAll();
	public Producto findById(Integer id);
	public boolean createUpdate(Producto producto);
	public boolean deleteByID(Integer id);
	public Producto createProducto(ProductoDTO producto);
}
