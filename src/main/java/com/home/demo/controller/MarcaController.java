package com.home.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.home.demo.entity.Marca;
import com.home.demo.service.IMarcaService;

@Controller
public class MarcaController {
	private IMarcaService serviceMarca;
	
	public MarcaController(final IMarcaService serviceMarca) {
		this.serviceMarca = serviceMarca;
	}
	
	@GetMapping("/marcas")
	public String getAll(Model modelo) {
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "marcas";
	}
	
	@GetMapping("/marcas/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("marca", new Marca());
		return "crearMarca";
	}
	
	
	@PostMapping("/marcas/guardar")
	public String create(Marca marca) {
		serviceMarca.createUpdate(marca, 0);
		
		return "redirect:/marcas";
	}
	
	@GetMapping("/marcas/editar/{id}")
	public String actualizar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("marca", serviceMarca.findById(id));
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearMarca";
	}
	
	@GetMapping("/marcas/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		serviceMarca.deleteByID(id);
		return "redirect:/marcas";
	}
}
