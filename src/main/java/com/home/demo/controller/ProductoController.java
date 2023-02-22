package com.home.demo.controller;

import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.demo.dto.ProductoDTO;
import com.home.demo.entity.Categoria;
import com.home.demo.entity.Producto;
import com.home.demo.service.ICategoriaService;
import com.home.demo.service.IProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
	private IProductoService serviceProducto;
	private ICategoriaService serviceCategoria;

	public ProductoController(IProductoService serviceProducto, ICategoriaService serviceCategoria) {
		this.serviceProducto = serviceProducto;
		this.serviceCategoria = serviceCategoria;
	}

	@GetMapping("")
	public String productos(Model modelo) {
		modelo.addAttribute("listaProductos", serviceProducto.getAll());
		return "productos";
	}

	@GetMapping("/nuevo")
	public String nuevoProducto(Model modelo) {
		List<Categoria> categorias = serviceCategoria.getAll();

		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("listaCategorias", categorias);
		return "crearProducto";
	}

	@PostMapping("/guardar")
	public String guardarProductos(Producto producto, HttpServletRequest request) {
		String[] detallesIDs = request.getParameterValues("detallesID");
		String[] detallesNombres = request.getParameterValues("detallesNombre");
		String[] detallesValores = request.getParameterValues("detallesValor");

		for(int i=0; i<detallesNombres.length; i++) {
			if(detallesIDs !=null  && detallesIDs.length>0) {
				producto.setDetalle(Integer.valueOf(detallesIDs[i]), detallesNombres[i], detallesValores[i]);
			}else {
				producto.anhadirDetalles(detallesNombres[i], detallesValores[i]);
			}
		}

		serviceProducto.createUpdate(producto);
		return "redirect:/productos";
	}

	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Integer id, Model model) {
		Producto producto = serviceProducto.findById(id);
		List<Categoria> categorias = serviceCategoria.getAll();

		model.addAttribute("producto", producto);
		model.addAttribute("listaCategorias", categorias);
		return "crearProducto";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id) {
		serviceProducto.deleteByID(id);
		return "redirect:/productos";
	}


	/** ***********************************************************************************************************************/

	@Operation(summary = "Devuelve una lista de los productos disponibles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devuelve una lista de los productos", content = {
					@Content(mediaType = "application/json")}
			)})
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getAll() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		String listaProductos = mapper.writeValueAsString(serviceProducto.getAll());
		return new ResponseEntity<>(listaProductos, HttpStatus.OK);
	}


	@Operation(summary = "Crea un nuevo producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Crea un nuevo producto", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
			})
	})
	@PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Producto> createProducto(@RequestBody ProductoDTO producto){
		return new ResponseEntity<Producto>(serviceProducto.createProducto(producto), HttpStatus.OK);
	}

	
	@Operation(summary = "Actualiza un producto existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualiza un producto pasandole su id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
			})
	})
	@PutMapping( path = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Producto> updateProducto(@RequestBody ProductoDTO producto){
		return new ResponseEntity<Producto>(serviceProducto.createProducto(producto), HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "Elimina un producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Elimina un producto pasandole su id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
			})
	})
	@DeleteMapping( path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> deleteProducto(@PathVariable("id") Integer id ){
		if(serviceProducto.deleteByID(id))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
	
}
