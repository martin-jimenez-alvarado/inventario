package com.home.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.demo.entity.Rol;
import com.home.demo.service.IRolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/rol")
@CrossOrigin(origins = "*")
public class RolController {
	private IRolService serviceRol;

	public RolController(final IRolService serviceRol) {
		this.serviceRol = serviceRol;
	}

	@Operation(summary = "Devuelve una lista con todos los roles")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Devuelve todos los roles", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Rol>> getAll() {
		return new ResponseEntity<List<Rol>>(serviceRol.getAll(), HttpStatus.OK);
	}

	@Operation(summary = "Actualiza el nombre del rol")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Devuelve el rol actualizado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class)) }) })
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Rol> updateRol(@RequestBody Rol rol) {
		return new ResponseEntity<Rol>(serviceRol.updateRol(rol), HttpStatus.OK);
	}

	@Operation(summary = "Crea un nuevo rol")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Devuelve el rol creado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class)) }) })
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
		return new ResponseEntity<Rol>(serviceRol.updateRol(rol), HttpStatus.OK);
	}

	@Operation(summary = "Elimina un rol existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Elimina un rol pasandole un id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) }) })
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Boolean>(serviceRol.removeByID(id), HttpStatus.OK);
	}

	@Operation(summary = "Devuelve el rol por el id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve el rol por su Identificador", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class)) }) })
	@GetMapping(path = "/findRol/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Rol> findRol(@PathVariable("id") Integer id) {
		Rol r = serviceRol.findByID(id);
		if (r != null)
			return new ResponseEntity<Rol>(r, HttpStatus.OK);
		else
			return new ResponseEntity<Rol>(HttpStatus.BAD_REQUEST);
	}

}
