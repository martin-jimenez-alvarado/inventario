package com.home.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.home.demo.entity.Categoria;
import com.home.demo.entity.Producto;
import com.home.demo.service.ICategoriaService;
import com.home.demo.service.IProductoService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProductoController {
	private IProductoService serviceProducto;
	private ICategoriaService serviceCategoria;

	public ProductoController(IProductoService serviceProducto, ICategoriaService serviceCategoria) {
		this.serviceProducto = serviceProducto;
		this.serviceCategoria = serviceCategoria;
	}

	@GetMapping("/productos")
	public String productos(Model modelo) {
		modelo.addAttribute("listaProductos", serviceProducto.getAll());
		return "productos";
	}

	@GetMapping("/productos/nuevo")
	public String nuevoProducto(Model modelo) {
		List<Categoria> categorias = serviceCategoria.getAll();

		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("listaCategorias", categorias);
		return "crearProducto";
	}

	@PostMapping("/productos/guardar")
	public String guardarProductos(Producto producto, HttpServletRequest request) {
		String[] detallesIDs = request.getParameterValues("detallesID");
		String[] detallesNombres = request.getParameterValues("detallesNombre");
		String[] detallesValores = request.getParameterValues("detallesValor");
		
		for(int i=0; i<detallesNombres.length; i++) {
			if(detallesIDs !=null  && detallesIDs.length>0) {
				producto.setDetalle(Integer.valueOf(detallesIDs[i]), detallesNombres[i], detallesValores[i]);
			}else {
				producto.ahadirDetalles(detallesNombres[i], detallesValores[i]);
			}
		}
		
		serviceProducto.createUpdate(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/productos/editar/{id}")
	public String editarProducto(@PathVariable("id") Integer id, Model model) {
		Producto producto = serviceProducto.findById(id);
		List<Categoria> categorias = serviceCategoria.getAll();
		
		model.addAttribute("producto", producto);
		model.addAttribute("listaCategorias", categorias);
		return "crearProducto";
	}
	
	@GetMapping("/productos/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id) {
		serviceProducto.deleteByID(id);
		return "redirect:/productos";
	}
}
