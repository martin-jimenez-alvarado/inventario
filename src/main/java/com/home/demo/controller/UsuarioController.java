package com.home.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.demo.dto.UsuarioDTO;
import com.home.demo.entity.Rol;
import com.home.demo.entity.Usuario;
import com.home.demo.repository.IRolRepositorio;
import com.home.demo.service.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
	private IUsuarioService serviceUser;
	private IRolRepositorio repoRol;

	public UsuarioController(IUsuarioService serviceUser, IRolRepositorio repoRol) {
		this.serviceUser = serviceUser;
		this.repoRol = repoRol;
	}

	@GetMapping()
	public String getAll(Model modelo) {
		modelo.addAttribute("listaUsuarios", serviceUser.getAll());
		return "usuarios";
	}

	@GetMapping("/nuevo")
	public String crearUsuario(Model modelo) {
		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("listaRoles", roles);
		modelo.addAttribute("usuario", new Usuario());
		return "crearUsuario";
	}

	@PostMapping("/guardar")
	public String guardarUsuario(Usuario user) {
		serviceUser.createUpdate(user);
		return "redirect:/usuarios";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model modelo) {
		Usuario user = serviceUser.findByID(id);
		modelo.addAttribute("usuario", user);

		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("listaRoles", roles);

		return "crearUsuario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		serviceUser.eliminar(id);
		return "redirect:/usuarios";
	}

	/*************************************************************************************************************************/

	@Operation(summary = "Devuelve una lista con todos los usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve una lista de usuarios", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Usuario>> getAll() {
		return new ResponseEntity<List<Usuario>>(serviceUser.getAll(), HttpStatus.OK);
	}

	@Operation(summary = "Elimina un usuario pasandole su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Devuelve verdadero o falso", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) }) })
	@DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> deleteByID(@PathVariable(name = "id", required = true) Integer id) {
		return new ResponseEntity<Boolean>(serviceUser.eliminar(id), HttpStatus.OK);
	}

	@Operation(summary = "Crea un nuevo usuario")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Devuelve el usuario creado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }) })
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> createUser(@RequestBody UsuarioDTO user) {
		Usuario u = serviceUser.createUser(user);
		if (u != null)
			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
		else
			return new ResponseEntity<Usuario>(u, HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "Actualiza un usuario registrado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve el usuario actualizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }) })
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> updateUser(@RequestBody UsuarioDTO user) {
		Usuario updateUser = serviceUser.updateUser(user);
		if (updateUser != null)
			return new ResponseEntity<Usuario>(updateUser, HttpStatus.OK);
		else
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "Busca un usuario por su email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve el usuario encontrado si existe, pasandole como valor su email", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }) })

	@GetMapping(path = "/findEmail/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> findByEmail(@PathVariable(name = "email", required = true) String email) {
		Usuario u = serviceUser.findByEmail(email);
		if (u != null)
			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
		else
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
	}
}
