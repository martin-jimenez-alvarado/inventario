package com.home.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/app")
public class InventarioController {

	@GetMapping(path = "/message/{message}")
	public ResponseEntity<String> simpleMessage(@PathVariable String message){
		return new ResponseEntity<>("test " + message, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/home")
	public String home() {
		return "";
	}
}
