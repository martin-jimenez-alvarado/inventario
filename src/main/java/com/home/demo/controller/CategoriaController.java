package com.home.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.home.demo.entity.Categoria;
import com.home.demo.service.ICategoriaService;
import com.home.demo.service.IMarcaService;


@Controller
public class CategoriaController {
	private ICategoriaService serviceCategoria;
	private IMarcaService serviceMarca;

	public CategoriaController(ICategoriaService serviceCategoria, IMarcaService serviceMarca) {
		this.serviceCategoria = serviceCategoria;
		this.serviceMarca = serviceMarca;
	}

	
	@GetMapping("/categorias")
	public String listarCategorias(Model modelo) {
		modelo.addAttribute("listaCategorias", serviceCategoria.getAll());
		return "categorias";
	}
	
	
	@GetMapping("/categorias/nuevo")
	public String mostrarFormulario(Model modelo) {
		modelo.addAttribute("categoria", new Categoria());
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearCategoria";
	}
	
	
	@GetMapping("/categorias/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("categoria", serviceCategoria.getById(id));
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearCategoria";
	}
	
	
	@PostMapping("/categorias/guardar")
	public String guardarCategoria(Categoria categoria) {
		serviceCategoria.createUpdateCategory(categoria);
		return "redirect:/categorias";
	}
	
	
	@GetMapping("/categorias/eliminar/{id}")
	public String eliminarCaategoria(@PathVariable("id") Integer id) {
		serviceCategoria.deleteByID(id);
		return "redirect:/categorias";
	}
}
