package com.home.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.demo.dto.ProductoDTO;
import com.home.demo.dto.ProductoDetalleDTO;
import com.home.demo.entity.Categoria;
import com.home.demo.entity.Producto;
import com.home.demo.entity.ProductoDetalle;
import com.home.demo.repository.IProductoRepositorio;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProductoServiceImp implements IProductoService {
	private IProductoRepositorio repoProducto;
	private ICategoriaService serviceCategoria;

	public ProductoServiceImp(IProductoRepositorio repoProducto, ICategoriaService serviceCategoria) {
		this.repoProducto = repoProducto;
		this.serviceCategoria = serviceCategoria;
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
		if (p != null) {
			p.setNombre(producto.getNombre());
			p.setPrecio(producto.getPrecio());
			p.setCategoria(producto.getCategoria());
			repoProducto.save(p);
		} else {
			repoProducto.save(producto);
		}
		return true;
	}

	/*
	 * Cualquier producto tiene que tener un detalle y una categoria
	 */
	@Override
	public Producto createProducto(ProductoDTO producto) {
		Producto productoExiste = null;
		Producto newProducto = null;

		int idCategoria = producto.getCategoria() != null ? producto.getCategoria().getId() : 0;
		int totalDetalles = producto.getDetalles().size();

		Categoria categoria = null;
		List<ProductoDetalle> detalles = null;

		if (idCategoria != 0) {
			productoExiste = producto.getId() != null ? findById(producto.getId()) : null;

			categoria = serviceCategoria.getById(producto.getCategoria().getId());
			if (categoria != null && productoExiste == null) {
				newProducto = new Producto();
				newProducto.setCategoria(categoria);
			}
				
			else if (categoria != null && productoExiste != null)
				productoExiste.setCategoria(categoria);
			if (totalDetalles > 0) {
				detalles = new ArrayList<>();
				if (productoExiste == null) {
					for (ProductoDetalleDTO productoDetalle : producto.getDetalles()) {
						detalles.add(
								new ProductoDetalle(productoDetalle.getNombre(), productoDetalle.getValor(), newProducto));
					}
					newProducto.setDetalles(detalles);
					newProducto.setNombre(producto.getNombre());
					newProducto.setPrecio(producto.getPrecio());
					log.info("_____" + newProducto.toString());
					return repoProducto.save(newProducto);
				} else if (productoExiste != null) {
					for (ProductoDetalleDTO productoDetalle : producto.getDetalles()) {
						detalles.add(
								new ProductoDetalle(productoDetalle.getNombre(), productoDetalle.getValor(), productoExiste));
					}
					productoExiste.setNombre(producto.getNombre());
					productoExiste.setPrecio(producto.getPrecio());
					productoExiste.setDetalles(detalles);
					log.info("_____" + productoExiste.toString());
					return repoProducto.save(productoExiste);
				}
			}
		}
		return productoExiste;
	}

	@Override
	public boolean deleteByID(Integer id) {
		Producto producto = findById(id);
		if (producto != null) {
			repoProducto.deleteById(id);
			return true;
		} else
			return false;
	}

}
