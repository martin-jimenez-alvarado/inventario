package com.home.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.home.demo.entity.Rol;
import com.home.demo.entity.Usuario;
import com.home.demo.repository.IRolRepositorio;
import com.home.demo.service.IUsuarioService;



@Controller
public class UsuarioController {
	private IUsuarioService serviceUser;
	private IRolRepositorio repoRol;
	
	public UsuarioController(IUsuarioService serviceUser, IRolRepositorio repoRol) {
		this.serviceUser = serviceUser;
		this.repoRol = repoRol;
	}
	
	
	@GetMapping("/usuarios")
	public String getAll(Model modelo) {
		modelo.addAttribute("listaUsuarios", serviceUser.getAll());
		return "usuarios";
	}
	
	
	@GetMapping("/usuarios/nuevo")
	public String crearUsuario(Model modelo) {
		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("listaRoles", roles);
		modelo.addAttribute("usuario", new Usuario());
		return "crearUsuario";
	}
	
	@PostMapping("/usuarios/guardar")
	public String guardarUsuario( Usuario user) {
		serviceUser.createUpdate(user);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model modelo) {
		Usuario user = serviceUser.findByID(id);
		modelo.addAttribute("usuario", user);
		
		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("listaRoles", roles);
		
		return "crearUsuario";
	}
	
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		serviceUser.eliminar(id);
		return "redirect:/usuarios";
	}
	
}
