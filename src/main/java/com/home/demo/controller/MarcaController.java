package com.home.demo.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.demo.entity.Marca;
import com.home.demo.service.IMarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {
	private IMarcaService serviceMarca;

	public MarcaController(final IMarcaService serviceMarca) {
		this.serviceMarca = serviceMarca;
	}

	@GetMapping("")
	public String getAll(Model modelo) {
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "marcas";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("marca", new Marca());
		return "crearMarca";
	}

	@PostMapping("/guardar")
	public String create(Marca marca) {
		serviceMarca.createUpdate(marca);

		return "redirect:/marcas";
	}

	@GetMapping("/editar/{id}")
	public String actualizar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("marca", serviceMarca.findById(id));
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearMarca";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		serviceMarca.deleteByID(id);
		return "redirect:/marcas";
	}

	/*************************************************************************************************************************/

	@Operation(summary = "Devuelve todas las categorias")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "categorias encontradas", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> all() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String response = mapper.writeValueAsString(serviceMarca.getAll());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Crea una categoria y devuelve true o false")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "crea una nueva categoria", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Marca.class)) }) })
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> newMarca(@RequestBody Marca marca) {
		return new ResponseEntity<>(serviceMarca.createUpdate(marca), HttpStatus.OK);
	}

	@Operation(summary = "Elimina una categoria por su nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Elimina una categoria por el nombre", content = {
					@Content(mediaType = "application/json") }) })
	@DeleteMapping(path = "/delete/{nombre}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteMarca(@PathVariable("nombre") final String nombre) {
		if (serviceMarca.deleteByNombre(nombre))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Actualiza el nombre de la categoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualiza la categoria", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Marca.class)) }) })
	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> updateMarca(@RequestBody Marca marca) {
		return new ResponseEntity<>(serviceMarca.createUpdate(marca), HttpStatus.OK);
	}

}
