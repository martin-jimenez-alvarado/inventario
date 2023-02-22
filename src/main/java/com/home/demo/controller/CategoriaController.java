package com.home.demo.controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.demo.entity.Categoria;
import com.home.demo.service.ICategoriaService;
import com.home.demo.service.IMarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
	private ICategoriaService serviceCategoria;
	private IMarcaService serviceMarca;

	public CategoriaController(ICategoriaService serviceCategoria, IMarcaService serviceMarca) {
		this.serviceCategoria = serviceCategoria;
		this.serviceMarca = serviceMarca;
	}

	@GetMapping("")
	public String listarCategorias(Model modelo) {
		modelo.addAttribute("listaCategorias", serviceCategoria.getAll());
		return "categorias";
	}

	@GetMapping("/nuevo")
	public String mostrarFormulario(Model modelo) {
		modelo.addAttribute("categoria", new Categoria());
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearCategoria";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("categoria", serviceCategoria.getById(id));
		modelo.addAttribute("listaMarcas", serviceMarca.getAll());
		return "crearCategoria";
	}

	@PostMapping("/guardar")
	public String guardarCategoria(Categoria categoria) {
		serviceCategoria.createUpdateCategory(categoria);
		return "redirect:/categorias";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarCaategoria(@PathVariable("id") Integer id) {
		serviceCategoria.deleteByID(id);
		return "redirect:/categorias";
	}

	/*************************************************************************************************************************/

	@Operation(summary = "Devuelve una lista de las marcas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve una lista de las marcas", content = {
					@Content(mediaType = "application/json") }) })

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAll() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String response = mapper.writeValueAsString(serviceCategoria.getAll());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Elimina una marca por el nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve true o false si fue exitosa", content = {
					@Content(mediaType = "application/json") }) })
	@DeleteMapping(path = "/delete/{nombre}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> eliminar(@PathVariable("nombre") final String nombre) {
		if (serviceCategoria.deleteByName(nombre))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Crea una nueva categoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Crea una nueva categoria o actualiza la existente con las marcas a las que pertenece", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }) })

	@PostMapping(path = "new", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> create(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(serviceCategoria.createUpdateCategory(categoria), HttpStatus.OK);
	}

	@Operation(summary = "Actualiza una categoria por su Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualiza una categoria existente o crea una nueva incluyendo las marcas a las que pertenece", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }) })
	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> update(@RequestBody Categoria categoria) {
		if (serviceCategoria.createUpdateCategory(categoria))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

}
